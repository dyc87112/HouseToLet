package com.nextplus.housetolet.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.nextplus.housetolet.domain.Payment;
import com.nextplus.housetolet.domain.Rental;
import com.nextplus.housetolet.domain.Room;
import com.nextplus.housetolet.domain.User;
import com.nextplus.housetolet.service.PaymentService;

@Service
public class PaymentServiceImpl extends BaseService implements PaymentService {

	@Override
	public Payment preFirstPayment(User user, Rental rental) {
		Payment payment = new Payment();
		payment.setWaterPrice(user.getWaterPrice());
		payment.setElectPrice(user.getElectPrice());
		// 设置结算开始时间和结束时间，以30天为一个周期算
		payment.setStartDate(rental.getStartDate());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(payment.getStartDate());
		calendar.add(Calendar.DATE, 30);
		payment.setEndDate(calendar.getTime());
		// 设置基础房租、押金、是否用网络、调整金额为0
		payment.setBasePayment(rental.getBasePayment());
		payment.setDeposit(rental.getDeposit());
		payment.setHasNet(rental.getHasNet());
		payment.setAdjustSum(0.0);
		return payment;
	}

	@Override
	public Payment preMonthPayment(User user, Rental rental, Payment lastPayment) {
		Payment payment = new Payment();
		payment.setWaterPrice(user.getWaterPrice());
		payment.setElectPrice(user.getElectPrice());
		payment.setBasePayment(rental.getBasePayment());
		payment.setAdjustSum(0.0);
		payment.setDeposit(0.0);
		payment.setStartDate(lastPayment.getEndDate());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(payment.getStartDate());
		calendar.add(Calendar.DATE, 30);
		payment.setEndDate(calendar.getTime());
		payment.setStartElect(lastPayment.getEndElect());
		payment.setStartWater(lastPayment.getEndWater());
		payment.setHasNet(rental.getHasNet());
		return payment;
	}

	@Override
	public Payment preLastPayment(User user, Rental rental, Payment lastPayment) {
		Payment payment = new Payment();
		payment.setWaterPrice(user.getWaterPrice());
		payment.setElectPrice(user.getElectPrice());
		payment.setBasePayment(0.0);
		payment.setDeposit(-rental.getDeposit());
		payment.setAdjustSum(0.0);
		payment.setStartElect(lastPayment.getEndElect());
		payment.setStartWater(lastPayment.getEndWater());
		payment.setHasNet(rental.getHasNet());
		payment.setStartDate(lastPayment.getEndDate());
		payment.setEndDate(lastPayment.getEndDate());
		return payment;
	}

	@Override
	public Payment saveFirstPayment(User user, Rental rental, Payment payment) {
		// 保存下一次缴费日期
		rental.setNextPayDate(payment.getEndDate());
		rental= rentalRepository.save(rental);
		
		payment.setType("开房");
		payment.setCreateDate(new Date());
		payment.setRental(rental);
		payment.setUser(user);
		payment.setRoom(rental.getRoom());
		payment.setEndElect(payment.getStartElect());
		payment.setEndWater(payment.getStartWater());
		payment.computePaymentAndInitDate();
		return paymentRepository.save(payment);
	}

	@Override
	public Payment saveMonthPayment(User user, Rental rental, Payment payment) {
		// 保存下一次缴费日期
		rental.setNextPayDate(payment.getEndDate());
		rental= rentalRepository.save(rental);
		
		payment.setType("月结");
		payment.setCreateDate(new Date());
		payment.setRental(rental);
		payment.setUser(user);
		payment.setRoom(rental.getRoom());
		payment.computePaymentAndInitDate();
		return paymentRepository.save(payment);
	}

	@Override
	public Payment saveLastPayment(User user, Rental rental, Payment payment) {
		rental.setIsValid(false);
		rental.setEndDate(new Date());
		rental = rentalRepository.save(rental);
		Room room = rental.getRoom();
		room.setIsEmpty(true);
		room = roomRepository.save(room);
		
		payment.setBasePayment(0.0);
		payment.setNetPay(0.0);
		payment.setType("退房");
		payment.setCreateDate(new Date());
		payment.setRental(rental);
		payment.setUser(user);
		payment.setRoom(room);
		payment.computeLastPayment();
		return paymentRepository.save(payment);
	}
	
	@Override
	public void deletePayment(Long paymentId) {
		Payment payment = paymentRepository.findOne(paymentId);
		Rental rental = payment.getRental();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String paymentEndDate = dateFormat.format(payment.getEndDate());
		String rentalNextDate = dateFormat.format(rental.getNextPayDate());
		if(paymentEndDate.equals(rentalNextDate)) {
			rental.setNextPayDate(payment.getStartDate());
			rentalRepository.save(rental);
		}
		paymentRepository.delete(payment);
	}

}
