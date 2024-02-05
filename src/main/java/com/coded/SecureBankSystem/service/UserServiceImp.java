package com.coded.SecureBankSystem.service;

import com.coded.SecureBankSystem.bo.user.CreateUserRequest;
import com.coded.SecureBankSystem.bo.user.Status;
import com.coded.SecureBankSystem.entity.UserEntity;
import com.coded.SecureBankSystem.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
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



        if (createUserRequest.getStatus().equals(Status.ACTIVE.name()) || createUserRequest.getStatus().equals(Status.INACTIVE.name())) {
            userEntity.setStatus(Status.valueOf(createUserRequest.getStatus()));
            userRepository.save(userEntity);
        } else {
            ResponseEntity.badRequest().body("Invalid status. Please write either ACTIVE or INACTIVE.");
        }
    }

//    @Override
//    public void saveUser(CreateUserRequest createUserRequest) {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setName(createUserRequest.getName());
//        userEntity.setEmail(createUserRequest.getEmail());
//        userEntity.setPhoneNumber(createUserRequest.getPhone());
//
//        if(createUserRequest.getStatus() != Status.ACTIVE.name() || Status.INACTIVE.name()){
//            ResponseEntity.badRequest();
//        }
//        userEntity.setStatus(Status.valueOf(createUserRequest.getStatus()));
//
//        userRepository.save(userEntity);
//
//    }


}
