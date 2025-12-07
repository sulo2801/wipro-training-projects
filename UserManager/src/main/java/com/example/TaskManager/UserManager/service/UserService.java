package com.example.TaskManager.UserManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskManager.UserManager.Entity.UserEntity;
import com.example.TaskManager.UserManager.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;
	
	public String register(UserEntity user) {
		if(repo.existsByUsername(user.getUsername()))
		return "Username already exists ❌";
	
	repo.save(user);
	return "user Saved sucrsfully🔰";
}
	   public List<UserEntity> getAllUsers() {
	        return repo.findAll();
	    }
}