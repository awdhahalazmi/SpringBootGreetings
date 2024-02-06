package com.coded.SecureBankSystem.service;

import com.coded.SecureBankSystem.bo.user.CreateUserRequest;
import com.coded.SecureBankSystem.bo.user.UpdateUserRequest;
import com.coded.SecureBankSystem.util.enums.Status;
import com.coded.SecureBankSystem.entity.UserEntity;
import com.coded.SecureBankSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(CreateUserRequest createUserRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(createUserRequest.getName());
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPhoneNumber(createUserRequest.getPhone());
//        if (!createUserRequest.getStatus().equals(Status.ACTIVE.name()) || !createUserRequest.getStatus().equals(Status.INACTIVE.name())) {
//            throw new IllegalArgumentException("Status should be written either ACTIVE or INACTIVE");
//
//        }
        userEntity.setStatus(Status.valueOf(createUserRequest.getStatus()));
        userRepository.save(userEntity);
    }

    @Override
    public void updateUserStatus(Long userId,UpdateUserRequest updateUserRequest) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow();
        if(!updateUserRequest.getStatus().equals("ACTIVE") && !updateUserRequest.getStatus().equals("INACTIVE")){
            throw new IllegalArgumentException("Status should be written either ACTIVE or INACTIVE");

        }
        userEntity.setStatus(Status.valueOf(updateUserRequest.getStatus()));
        userRepository.save(userEntity);
    }





}



