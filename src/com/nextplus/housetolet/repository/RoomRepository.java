package com.nextplus.housetolet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.nextplus.housetolet.domain.Room;
import com.nextplus.housetolet.domain.User;

public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {

	@Query("from Room r where r.user = :user order by r.name")
	List<Room> findByUser(@Param("user")User user);
	
	@Query("from Room r where r.user = :user and r.isEmpty = true order by r.name")
	List<Room> findEmptyRoomByUser(@Param("user") User user);

}