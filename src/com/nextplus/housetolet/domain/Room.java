package com.nextplus.housetolet.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nextplus.common.domain.SuperEntity;

/**
 * 房间实体
 * 
 * @author didi
 *
 * 创建时间：2013-1-3
 *
 */
@Entity
@Table(name = "room")
public class Room extends SuperEntity {

	private String name;
	private String info;
	private Boolean isEmpty;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@OneToMany(mappedBy = "room")
	private List<Rental> rentals;
	@OneToMany(mappedBy = "room")
	private List<Payment> payments;

	public List<Rental> getRentals() {
		return rentals;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getIsEmpty() {
		return isEmpty;
	}

	public void setIsEmpty(Boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
}
