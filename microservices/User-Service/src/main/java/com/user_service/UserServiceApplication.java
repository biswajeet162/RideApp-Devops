package com.user_service;

import com.user_service.model.User;
import com.user_service.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}


	@PostConstruct
	public void addSampleUsers() {
		for (int i = 1; i <= 10; i++) {
			User user = new User();
			user.setName("Sample User " + i);
			user.setEmail("user" + i + "@example.com");
			user.setPassword("pass" + i);
			user.setRole(i % 2 == 0 ? "driver" : "user"); // Alternate between "user" and "driver"
			user.setPhoneNumber("123456789" + i); // Unique phone number for each user

			userRepository.save(user);
		}
	}

}
