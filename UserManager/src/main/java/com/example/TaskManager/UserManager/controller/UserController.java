package com.example.TaskManager.UserManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaskManager.UserManager.Entity.UserEntity;
import com.example.TaskManager.UserManager.service.UserService;

@RestController
@RequestMapping("/api/SE")
public class UserController {
	
	
	@Autowired
	public UserService service;
	
	@PostMapping("/register")
	public String register(@RequestBody UserEntity user) {
		return service.register(user);
	}
//	
//	@GetMapping("/GetAll")
//	  public ResponseEntity<List<UserEntity>> getAllUsers() {
//        return ResponseEntity.ok(UserService.getAllUsers());
//    }

}
