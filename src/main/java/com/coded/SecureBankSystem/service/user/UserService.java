package com.coded.SecureBankSystem.service.user;

import com.coded.SecureBankSystem.bo.user.CreateUserRequest;
import com.coded.SecureBankSystem.bo.user.UpdateUserStatusRequest;
import com.coded.SecureBankSystem.entity.UserEntity;

import java.util.List;

public interface UserService {
    void saveUser(CreateUserRequest createUserRequest);
     void updateUserStatus(Long userId, UpdateUserStatusRequest updateUserStatusRequest);
     List<String> getALlUsersWithStrongPassword();
}




