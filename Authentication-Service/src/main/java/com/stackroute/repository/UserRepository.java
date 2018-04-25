package com.stackroute.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	User findByEmailId(String emailId);
}
