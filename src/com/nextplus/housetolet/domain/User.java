package com.nextplus.housetolet.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.nextplus.common.domain.SuperEntity;

/**
 * 用户
 * 
 * @author didi
 *
 * 创建时间：2013-1-3
 *
 */
@Entity
@Table(name = "user")
public class User extends SuperEntity {
	
	@Length(min = 5, max = 20, message = "{username.length}")
	@Column(length = 20, unique = true, nullable = false)
	private String username;
	@Length(min = 5, max = 20, message = "{password.length}")
	@Column(length = 20, nullable = false)
	private String password;
	@Email(message="{email.invalid}")
	@Length(max = 200, message = "{email.length}")
	@Column(length = 200, unique = true)
	private String email;
	
	private Double electPrice;		// 水费单价
	private Double waterPrice;		// 电费单价
	private Double netPrice;		// 网费单价
	
	@OneToMany(mappedBy="user")
	private List<Room> rooms;
	@OneToMany(mappedBy="user")
	private List<Rental> rentals;
	@OneToMany(mappedBy="user")
	private List<Payment> payments;
	
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

	public Double getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(Double netPrice) {
		this.netPrice = netPrice;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

}
