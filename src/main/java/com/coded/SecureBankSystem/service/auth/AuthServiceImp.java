package com.coded.SecureBankSystem.service.auth;

import com.coded.SecureBankSystem.bo.auth.AuthenticationResponse;
import com.coded.SecureBankSystem.bo.auth.CreateLoginRequest;
import com.coded.SecureBankSystem.bo.auth.CreateSignupRequest;
import com.coded.SecureBankSystem.bo.auth.LogoutResponse;
import com.coded.SecureBankSystem.bo.customUserDetails.CustomUserDetails;
import com.coded.SecureBankSystem.config.JWTUtil;
import com.coded.SecureBankSystem.entity.RoleEntity;
import com.coded.SecureBankSystem.entity.UserEntity;
import com.coded.SecureBankSystem.repository.RoleRepository;
import com.coded.SecureBankSystem.repository.UserRepository;
import com.coded.SecureBankSystem.util.enums.Roles;
import com.coded.SecureBankSystem.util.enums.Status;
import com.coded.SecureBankSystem.util.exception.BodyGuardException;
import com.coded.SecureBankSystem.util.exception.UserNotFoundException;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService{
private final AuthenticationManager authenticationManager;
private final CustomUserDetailService userDetailService;
private final JWTUtil jwtUtil;
private final BCryptPasswordEncoder bCryptPasswordEncoder;
private  final RoleRepository roleRepository;
private final UserRepository userRepository;

    public AuthServiceImp(AuthenticationManager authenticationManager, CustomUserDetailService userDetailService, JWTUtil jwtUtil, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void signup(CreateSignupRequest createSignupRequest) {
        RoleEntity roleEntity= roleRepository.findRoleEntityByTitle(Roles.user.name())
                .orElseThrow(() -> new BodyGuardException("no Roles Found"));;
        UserEntity user= new UserEntity();
        user.setName(createSignupRequest.getName());
        user.setUsername(createSignupRequest.getUsername());
        user.setPhoneNumber(createSignupRequest.getPhoneNumber());
        user.setEmail(createSignupRequest.getEmail());
        user.setRole(roleEntity);
        user.setStatus(Status.ACTIVE);
        user.setPassword(bCryptPasswordEncoder.encode(createSignupRequest.getPassword()));
        userRepository.save(user);
    }


    @Override
    public AuthenticationResponse login(CreateLoginRequest createLoginRequest) {
        requiredNonNull(createLoginRequest.getUsername(),"username");
        requiredNonNull(createLoginRequest.getPassword(),"password");

        String username= createLoginRequest.getUsername().toLowerCase();
        String password= createLoginRequest.getPassword();
        authentication(username,password);

        CustomUserDetails userDetails = userDetailService.loadUserByUsername(username);
        String accessToken = jwtUtil.generateToken(userDetails);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setId(userDetails.getId());
        response.setUsername(userDetails.getUsername());
        response.setRole(userDetails.getRole());
        response.setToken("Bearer"+ accessToken);
        return response;



    }

    @Override
    public void logout(LogoutResponse logoutResponse) {
        requiredNonNull(logoutResponse.getToken(),"Token");

    }
    private void requiredNonNull(Object obj,String name){
        if(obj == null || obj.toString().isEmpty()){
            throw new BodyGuardException(name+"can not be empty");
        }
    }
    private void authentication(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (BodyGuardException e){
            throw new BodyGuardException("Incorrect password");
        }catch (AuthenticationServiceException e){
            throw new UserNotFoundException("Incorrect username");
        }
    }
}
