package com.nextplus.housetolet.domain;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nextplus.common.domain.SuperEntity;

/**
 * 账单
 * 
 * @author didi
 *
 * 创建时间：2013-1-3
 *
 */
@Entity
@Table(name = "payment")
public class Payment extends SuperEntity {

	// 前台表单中填写数据
	private Date endDate;				// 结束日期
	private Double endElect;			// 结束电表读数
	private Double endWater;			// 结束水表读数
	private Double deposit;				// 押金
	private Double adjustPrice;			// 结转零头
	private Double adjustSum;			// 其他费用 
	private String adjustInfo;			// 其他费用说明
	private Date createDate;			// 账单创建日期
	private String type;				// 类型：开房、退房、月结

	// 从User中获得
	private Double electPrice;			// 电费单价
	private Double waterPrice;			// 水费单价
	private Double netPrice;			// 网费单价
	
	// 以下属性通过Rental中获得
	private Boolean hasNet;				// 是否使用网络
	private Double basePayment;			// 基础房租
	// 以下属性通过上一次payment初始化
	private Date startDate;				// 起租日期
	private Double startElect;			// 起租电表读数
	private Double startWater;			// 起租水表读数
	// 以下费用通过computePayment*方法来计算
	private Double electPay;			// 电费
	private Double waterPay;			// 水费
	private Double netPay;				// 网络使用费
	private Double sumPay;				// 总费用
	
	private Boolean hasPayed; 			// 是否已经支付
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name="rental_id")
	private Rental rental;

	/**
	 * 计算各种费用以及总价（不带调整价格）
	 * @param user
	 */
	public void computePaymentAndInitDate() {
		DecimalFormat df = new DecimalFormat("0.00");
		// 计算水费、电费、网费 
		setElectPay(getElectPrice() * (getEndElect() - getStartElect()));
		setWaterPay(getWaterPrice() * (getEndWater() - getStartWater()));
		netPay = hasNet ? getNetPrice() : 0;
		// 计算水费、电费、网费、基础租金、押金、其他费用的总和，并四舍五入计算结转零头
		double sum = getBasePayment() + getDeposit() + getElectPay() + getWaterPay() + getNetPay() + getAdjustSum();
		long sumRound = Math.round(sum);
		setSumPay(Double.valueOf(sumRound));
		BigDecimal sumB = new BigDecimal(sum);
		BigDecimal sumR = new BigDecimal(sumRound);
		BigDecimal decimal = sumR.subtract(sumB);
		setAdjustPrice(decimal.doubleValue());
		
		setElectPay(Double.valueOf(df.format(getElectPay())));
		setWaterPay(Double.valueOf(df.format(getWaterPay())));
		setAdjustPrice(Double.valueOf(df.format(getAdjustPrice())));
		
		// 设置状态为未缴纳房租
		setHasPayed(false);
	}
	
	/**
	 * 计算退房时候的押金和水电
	 */
	public void computeLastPayment() {
		DecimalFormat df = new DecimalFormat("0.00");
		// 计算水费、电费、网费 
		setElectPay(getElectPrice() * (getEndElect() - getStartElect()));
		setWaterPay(getWaterPrice() * (getEndWater() - getStartWater()));
		double sum = getElectPay() + getWaterPay() + getAdjustSum() + getDeposit();
		long sumRound = Math.round(sum);
		setSumPay(Double.valueOf(sumRound));
		BigDecimal sumB = new BigDecimal(sum);
		BigDecimal sumR = new BigDecimal(sumRound);
		BigDecimal decimal = sumR.subtract(sumB);
		setAdjustPrice(decimal.doubleValue());
		
		setElectPay(Double.valueOf(df.format(getElectPay())));
		setWaterPay(Double.valueOf(df.format(getWaterPay())));
		setAdjustPrice(Double.valueOf(df.format(getAdjustPrice())));
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Double getStartElect() {
		return startElect;
	}

	public void setStartElect(Double startElect) {
		this.startElect = startElect;
	}

	public Double getEndElect() {
		return endElect;
	}

	public void setEndElect(Double endElect) {
		this.endElect = endElect;
	}

	public Double getElectPay() {
		return electPay;
	}

	public void setElectPay(Double electPay) {
		this.electPay = electPay;
	}

	public Double getStartWater() {
		return startWater;
	}

	public void setStartWater(Double startWater) {
		this.startWater = startWater;
	}

	public Double getEndWater() {
		return endWater;
	}

	public void setEndWater(Double endWater) {
		this.endWater = endWater;
	}

	public Double getWaterPay() {
		return waterPay;
	}

	public void setWaterPay(Double waterPay) {
		this.waterPay = waterPay;
	}

	public Boolean getHasNet() {
		return hasNet;
	}

	public void setHasNet(Boolean hasNet) {
		this.hasNet = hasNet;
	}

	public Double getNetPay() {
		return netPay;
	}

	public void setNetPay(Double netPay) {
		this.netPay = netPay;
	}

	public Double getBasePayment() {
		return basePayment;
	}

	public void setBasePayment(Double basePayment) {
		this.basePayment = basePayment;
	}

	public Double getAdjustSum() {
		return adjustSum;
	}

	public void setAdjustSum(Double adjustSum) {
		this.adjustSum = adjustSum;
	}

	public String getAdjustInfo() {
		return adjustInfo;
	}

	public void setAdjustInfo(String adjustInfo) {
		this.adjustInfo = adjustInfo;
	}

	public Double getSumPay() {
		return sumPay;
	}

	public void setSumPay(Double sumPay) {
		this.sumPay = sumPay;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Rental getRental() {
		return rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}

	public Boolean getHasPayed() {
		return hasPayed;
	}

	public void setHasPayed(Boolean hasPayed) {
		this.hasPayed = hasPayed;
	}

	public Double getElectPrice() {
		return electPrice;
	}

	public void setElectPrice(Double electPrice) {
		this.electPrice = electPrice;
	}

	public Double getWaterPrice() {
		return waterPrice;
	}

	public void setWaterPrice(Double waterPrice) {
		this.waterPrice = waterPrice;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAdjustPrice() {
		return adjustPrice;
	}

	public void setAdjustPrice(Double adjustPrice) {
		this.adjustPrice = adjustPrice;
	}

	public Double getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(Double netPrice) {
		this.netPrice = netPrice;
	}
	
}
