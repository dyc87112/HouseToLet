package com.nextplus.housetolet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.nextplus.housetolet.domain.Payment;
import com.nextplus.housetolet.domain.Rental;
import com.nextplus.housetolet.domain.User;

public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long> {

	@Query("from Payment p where p.user = :user order by p.endDate desc, p.startDate desc")
	List<Payment> findByUserDateDesc(@Param("user") User user);
	
	@Query("from Payment p where p.user = :user and p.rental = :rental")
	List<Payment> findLastByUserAndRental(@Param("user") User user, @Param("rental") Rental rental);
	
	@Query("from Payment p where p.user = :user and p.rental = :rental order by p.createDate")
	List<Payment> findByUserAndRentalASC(@Param("user") User user, @Param("rental") Rental rental);
	
	@Query("from Payment p where p.user = :user and p.rental = :rental order by p.createDate desc")
	List<Payment> findByUserAndRentalDESC(@Param("user") User user, @Param("rental") Rental rental);
	
	@Query("from Payment p where p.user = :user and p.rental.id = :rentalId order by p.createDate desc")
	List<Payment> findByUserAndRentalIdDESC(@Param("user") User user, @Param("rentalId") Long rentalId);
}
