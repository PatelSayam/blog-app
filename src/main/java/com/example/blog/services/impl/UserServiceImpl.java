package com.example.blog.services.impl;

import com.example.blog.entities.User;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payloads.UserDTO;
import com.example.blog.repositories.UserRepo;
import com.example.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = this.dtoToUser(userDTO);
        User savedUser = userRepo.save(user);
        return this.userToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setAbout(userDTO.getAbout());

        User updatedUser = this.userRepo.save(user);
        UserDTO userDTO1 = this.userToDTO(updatedUser);
        return userDTO1;
    }

    @Override
    public UserDTO getUserById(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return this.userToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> users = this.userRepo.findAll();
        List<UserDTO> userDTOS = users.stream()
                                        .map(user -> this.userToDTO(user))
                                        .toList();
        return userDTOS;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO, User.class);

//        user.setId(userDTO.getId());
//        user.setName(userDTO.getName());
//        user.setEmail(userDTO.getEmail());
//        user.setAbout(userDTO.getAbout());
//        user.setPassword(userDTO.getPassword());
        return user;
    }

    private UserDTO userToDTO(User user) {
        UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

}
