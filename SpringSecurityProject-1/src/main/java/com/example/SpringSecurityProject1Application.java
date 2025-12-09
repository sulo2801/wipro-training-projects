package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class SpringSecurityProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityProject1Application.class, args);
	}
	@GetMapping("/welcome")
    public String welcomeMessage() {
        return "Home Page";
    }

	

}
