package com.nextplus.housetolet.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.nextplus.common.domain.SuperEntity;

/**
 * 租赁关系
 * 
 * @author didi
 *
 * 创建时间：2013-1-3
 *
 */
@Entity
@Table(name = "rental")
public class Rental extends SuperEntity {

	@NotBlank
	private String customerName;		// 客户姓名
	@NotBlank
	private String customerId;			// 客户证件号码
	@NotBlank
	private String customerTel;			// 客户电话
	
	private Boolean hasNet;				// 是否使用网络
	private Double basePayment;			// 基础房租
	private Double deposit;				// 押金

	private String otherInfo;			// 其他备注信息
	private Date createDate;			// 创建日期
	private Date startDate;				// 租赁开始日期
	private Date nextPayDate;			// 下一次结算日期
	private Date endDate;				// 租赁结束日期
	
	private Boolean isValid;			// 是否有效

	@ManyToOne
	@JoinColumn(name="room_id")
	private Room room;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@OneToMany(mappedBy="rental")
	private List<Payment> payments = new ArrayList<Payment>();
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Boolean getHasNet() {
		return hasNet;
	}
	public void setHasNet(Boolean hasNet) {
		this.hasNet = hasNet;
	}
	public Double getBasePayment() {
		return basePayment;
	}
	public void setBasePayment(Double basePayment) {
		this.basePayment = basePayment;
	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
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
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
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
	public Date getNextPayDate() {
		return nextPayDate;
	}
	public void setNextPayDate(Date nextPayDate) {
		this.nextPayDate = nextPayDate;
	}

}
