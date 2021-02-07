package com.uj.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uj.registration.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailId(String emailId);

	User findByEmailIdAndPassword(String emailId, String password);

}
