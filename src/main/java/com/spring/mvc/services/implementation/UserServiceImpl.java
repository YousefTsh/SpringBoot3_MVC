package com.spring.mvc.services.implementation;

import com.spring.mvc.dto.RegistrationDto;
import com.spring.mvc.models.Role;
import com.spring.mvc.models.UserEntity;
import com.spring.mvc.repositorys.RoleRepository;
import com.spring.mvc.repositorys.UserRepository;
import com.spring.mvc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        if(registrationDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        }
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        if (registrationDto.getEmail() != null && registrationDto.getPassword() != null){
            user.setApp("internal");
        }
        else{
            user.setApp(registrationDto.getApp());
        }
        userRepository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity findFirstByUsernameApp(String username, String App) {
        return userRepository.findFirstByUsernameAndApp(username,App);
    }
}
