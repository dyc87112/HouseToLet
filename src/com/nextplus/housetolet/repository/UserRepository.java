package com.nextplus.housetolet.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nextplus.housetolet.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByUsername(String username);
	
	User findByEmail(String email);
	
}
