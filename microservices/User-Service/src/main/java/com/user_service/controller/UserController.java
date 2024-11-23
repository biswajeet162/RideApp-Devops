package com.user_service.controller;

import com.user_service.dto.UserRequestDto;
import com.user_service.dto.UserResponseDto;
import com.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRequestDto userRequest) {
        return userService.registerUser(userRequest);
    }

    @PostMapping("/login")
    public UserResponseDto login(@RequestParam String email, @RequestParam String password) {
        return userService.login(email, password);
    }

    @GetMapping("/profile/{id}")
    public UserResponseDto getUserProfile(@PathVariable Long id) {
        return userService.getUserProfile(id);
    }

    @GetMapping("/profile/all")
    public List<UserResponseDto> getAllUserProfile() {
        return userService.getAllUserProfile();
    }
}

