package com.coded.SecureBankSystem.service.admin;

import com.coded.SecureBankSystem.entity.UserEntity;
import com.coded.SecureBankSystem.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class AdminServiceImp implements AdminService{
    private final UserRepository userRepository;

    public AdminServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
