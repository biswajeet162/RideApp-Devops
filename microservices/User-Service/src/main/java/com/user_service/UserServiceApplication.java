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

		User user1 = new User();
		user1.setName("Biswajeet User");
		user1.setEmail("biswauser@example.com");
		user1.setPassword("pass");
		user1.setRole("user"); // Alternate between "user" and "driver"
		user1.setPhoneNumber("9997455512"); // Unique phone number for each user
		userRepository.save(user1);

		User user2 = new User();
		user2.setName("Biswajeet Driver");
		user2.setEmail("biswadriver@example.com");
		user2.setPassword("pass");
		user2.setRole("driver"); // Alternate between "user" and "driver"
		user2.setPhoneNumber("9997455512"); // Unique phone number for each user
		userRepository.save(user2);

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
