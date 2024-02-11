package com.coded.SecureBankSystem.service.user;

import com.coded.SecureBankSystem.bo.user.CreateUserRequest;
import com.coded.SecureBankSystem.bo.user.UpdateUserStatusRequest;
import com.coded.SecureBankSystem.util.enums.Status;
import com.coded.SecureBankSystem.entity.UserEntity;
import com.coded.SecureBankSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public void updateUserStatus(Long userId, UpdateUserStatusRequest updateUserStatusRequest) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow();
        if(!updateUserStatusRequest.getStatus().equals("ACTIVE") && !updateUserStatusRequest.getStatus().equals("INACTIVE")){
            throw new IllegalArgumentException("Status should be written either ACTIVE or INACTIVE");

        }
        userEntity.setStatus(Status.valueOf(updateUserStatusRequest.getStatus()));
        userRepository.save(userEntity);
    }

    @Override
    public List<String> getALlUsersWithStrongPassword() {
        return userRepository.findAll().stream()
                .filter(user -> user.getPassword().length() > 8)
                .map(UserEntity::getName)
                .collect(Collectors.toList());
    }



}



