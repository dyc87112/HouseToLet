package com.nextplus.housetolet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.nextplus.housetolet.domain.Rental;
import com.nextplus.housetolet.domain.Room;
import com.nextplus.housetolet.domain.User;

public interface RentalRepository extends PagingAndSortingRepository<Rental, Long> {

	List<Rental> findAllByUser(User user);
	
	@Query("from Rental r where r.user = :user and r.isValid = true order by r.nextPayDate")
	List<Rental> findCurrentRentalByUserASCNextPayDate(@Param("user") User user);
	
	@Query("from Rental r where r.user = :user and r.isValid = false order by r.room.name")
	List<Rental> findHistoryRentalByUser(@Param("user") User user);

	@Query("from Rental r where r.user = :user and r.isValid = true order by r.room.name")
	List<Rental> findCurrentRentalByUser(@Param("user") User user);
	
	@Query("from Rental r where r.user = :user and r.room = :room and r.isValid = false order by r.room.name")
	List<Rental> findAllRentalByUserAndRoom(@Param("user") User user, @Param("room") Room room);
	
}
