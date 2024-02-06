package com.coded.SecureBankSystem.service;

import com.coded.SecureBankSystem.bo.user.CreateUserRequest;
import com.coded.SecureBankSystem.bo.user.UpdateUserRequest;

public interface UserService {
    void saveUser(CreateUserRequest createUserRequest);
     void updateUserStatus(UpdateUserRequest updateUserRequest);
}
