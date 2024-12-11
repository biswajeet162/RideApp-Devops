package com.user_service.service;

import com.user_service.dto.UserRequestDto;
import com.user_service.dto.UserResponseDto;
import com.user_service.model.User;
import com.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto registerUser(UserRequestDto userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword()); // Plaintext for now
        user.setRole(userRequest.getRole());
        user.setPhoneNumber(userRequest.getPhoneNumber());

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    public UserResponseDto login(String email, String password) {
        log.info("user details - ---  {} {}", email, password);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        return mapToResponse(user);
    }

    public UserResponseDto getUserProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponse(user);
    }

    public List<UserResponseDto> getAllUserProfile() {
        List<User> allUsers = userRepository.findAll();

        List<UserResponseDto> allUsersMapped = new ArrayList<>();

        allUsers.forEach(user-> {
            allUsersMapped.add(mapToResponse(user));
        });

        return allUsersMapped;
    }

    private UserResponseDto mapToResponse(User user) {
        UserResponseDto response = new UserResponseDto();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setPhoneNumber(user.getPhoneNumber());
        return response;
    }
}
