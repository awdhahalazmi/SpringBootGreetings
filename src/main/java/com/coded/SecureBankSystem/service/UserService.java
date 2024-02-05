package com.coded.SecureBankSystem.service;

import com.coded.SecureBankSystem.bo.user.CreateUserRequest;
import com.coded.SecureBankSystem.bo.user.Status;
import com.coded.SecureBankSystem.entity.UserEntity;

import java.util.List;

public interface UserService {
    void saveUser(CreateUserRequest createUserRequest);
    //void status (Status status);
}
