package com.coded.SecureBankSystem.service.auth;

import com.coded.SecureBankSystem.bo.auth.AuthenticationResponse;
import com.coded.SecureBankSystem.bo.auth.CreateLoginRequest;
import com.coded.SecureBankSystem.bo.auth.CreateSignupRequest;
import com.coded.SecureBankSystem.bo.auth.LogoutResponse;

public interface AuthService {
    void signup(CreateSignupRequest createSignupRequest);


    AuthenticationResponse login(CreateLoginRequest createLoginRequest);
    void logout(LogoutResponse logoutResponse);
}
