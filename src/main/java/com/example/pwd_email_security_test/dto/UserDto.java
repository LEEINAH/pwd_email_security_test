package com.example.pwd_email_security_test.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private int userPk;
    private String userId;
    private String userPwd;
    private String email;
}
