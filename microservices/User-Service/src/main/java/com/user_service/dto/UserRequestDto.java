package com.user_service.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
    private String role; // "user" or "driver"
    private String phoneNumber;
}
