package com.cg.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.atm.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUserId(long userId);

	User deleteByUserId(long userId);

}
