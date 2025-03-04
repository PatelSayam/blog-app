package com.example.blog.controllers;

import com.example.blog.entities.User;
import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.UserDTO;
import com.example.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createUserDTO = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createUserDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable("userId") Integer uid) {
        UserDTO updateUserDTO = this.userService.updateUser(userDTO, uid);
        return ResponseEntity.ok(updateUserDTO);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
        this.userService.deleteUser(uid);
        return new ResponseEntity<ApiResponse>( new ApiResponse("User deleted Successfully", true), HttpStatus.OK );
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getSingleUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
