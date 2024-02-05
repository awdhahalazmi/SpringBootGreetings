package com.coded.SecureBankSystem.controller.userController;


import com.coded.SecureBankSystem.bo.user.CreateUserRequest;
import com.coded.SecureBankSystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<java.lang.String> createUser(@RequestBody CreateUserRequest createUserRequest) {
        userService.saveUser(createUserRequest);
        return ResponseEntity.ok("A User Has Been Created");

    }

//    @GetMapping("/search-user")
//
//    public ResponseEntity<java.lang.String> updateStatus(@RequestParam Long id,
//                                                         @RequestParam String status) {
//
//
//        return ResponseEntity.ok("A User Has Been Created");
//
//    }
}

