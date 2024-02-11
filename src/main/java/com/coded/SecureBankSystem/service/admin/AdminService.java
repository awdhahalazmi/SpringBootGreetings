package com.coded.SecureBankSystem.service.admin;

import com.coded.SecureBankSystem.entity.UserEntity;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface AdminService {

     public List<UserEntity> getAllUsers();
}
