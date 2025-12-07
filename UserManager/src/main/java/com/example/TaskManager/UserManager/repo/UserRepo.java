package com.example.TaskManager.UserManager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TaskManager.UserManager.Entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long>{
	
	boolean existsByUsername(String username);

}
