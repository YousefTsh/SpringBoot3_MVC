package com.spring.mvc.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegistrationDto {
    private Integer id;
    @NotEmpty(message = "username can not be empty")
    private String username;
    @NotEmpty(message = "email can not be empty")
    private String email;
    @NotEmpty(message = "password can not be empty")
    private String password;
    private String app;
}
