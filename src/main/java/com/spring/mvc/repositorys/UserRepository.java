package com.spring.mvc.repositorys;

import com.spring.mvc.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String userName);

    UserEntity findFirstByUsername(String username);
}
