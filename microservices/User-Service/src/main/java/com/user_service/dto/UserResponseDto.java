package com.user_service.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String role;
    private String phoneNumber;
}
