package com.spring.mvc.services;

import com.spring.mvc.dto.RegistrationDto;
import com.spring.mvc.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
