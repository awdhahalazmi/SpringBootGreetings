package com.coded.SecureBankSystem.controller.userController;


import com.coded.SecureBankSystem.bo.user.CreateUserRequest;
import com.coded.SecureBankSystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService; //injection this is the constructor with similar class name

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) {
        try {
            userService.saveUser(createUserRequest);


        }catch (IllegalArgumentException e){
//            System.out.println("Error please write ACTIVE or INACTIVE");
            return ResponseEntity.badRequest().body("Status should be written either ACTIVE or INACTIVE");
        }
        //userService.saveUser(createUserRequest);
        return ResponseEntity.ok("A User Has Been Created");
    }

}

