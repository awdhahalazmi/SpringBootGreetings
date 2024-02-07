package com.coded.SecureBankSystem.service.user;

import com.coded.SecureBankSystem.bo.user.CreateUserRequest;
import com.coded.SecureBankSystem.bo.user.UpdateUserStatusRequest;

public interface UserService {
    void saveUser(CreateUserRequest createUserRequest);
     void updateUserStatus(Long userId, UpdateUserStatusRequest updateUserStatusRequest);
}
