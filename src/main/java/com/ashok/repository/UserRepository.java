package com.ashok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ashok.entity.User;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("From User where userEmail=:userEmail")
	public User getUser(@Param("userEmail") String userEmail);
	@Modifying
	@Query("update User u set u.userPwd=:userPwd,u.accStatus = 'Unlocked' where u.userEmail=:userEmail") 
	public void updatePassword(@Param("userPwd")String userPwd,@Param("userEmail") String userEmail);
}
