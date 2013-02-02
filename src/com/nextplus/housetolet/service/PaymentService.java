package com.nextplus.housetolet.service;

import org.springframework.transaction.annotation.Transactional;

import com.nextplus.housetolet.domain.Payment;
import com.nextplus.housetolet.domain.Rental;
import com.nextplus.housetolet.domain.User;

public interface PaymentService {
	
	/**
	 * 删除账单，并且修改租赁关系中的下一次结算日期
	 * 
	 * @param paymentId
	 */
	@Transactional(rollbackFor=Exception.class) 
	void deletePayment(Long paymentId);
	
	/**
	 * 准备首次账单的表单
	 * 
	 * @param user
	 * @param rental
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class) 
	Payment preFirstPayment(User user, Rental rental);
	
	/**
	 * 准备月结账单的表单
	 * 
	 * @param user
	 * @param rental
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class) 
	Payment preMonthPayment(User user, Rental rental, Payment lastPayment);

	/**
	 * 准备退房账单的表单
	 * 
	 * @param user
	 * @param rental
	 * @param lastPayment
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class) 
	Payment preLastPayment(User user, Rental rental, Payment lastPayment);
	
	/**
	 * 保存首次账单
	 * 
	 * @param user
	 * @param rental
	 * @param payment
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class) 
	Payment saveFirstPayment(User user, Rental rental, Payment payment);
	
	/**
	 * 保存月结账单
	 * 
	 * @param user
	 * @param rental
	 * @param payment
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class) 
	Payment saveMonthPayment(User user, Rental rental, Payment payment);
	
	/**
	 * 保存退房账单
	 * 
	 * @param user
	 * @param rental
	 * @param payment
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class) 
	Payment saveLastPayment(User user, Rental rental, Payment payment);

}
