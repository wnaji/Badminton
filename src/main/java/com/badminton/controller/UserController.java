package com.badminton.controller;

import com.badminton.converter.UserDTOConverter;
import com.badminton.dto.UserDTO;
import com.badminton.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserDTOConverter userDTOConverter;

    @Autowired
    public UserController(UserRepository userRepository, UserDTOConverter userDTOConverter) {
        this.userRepository = userRepository;
        this.userDTOConverter = userDTOConverter;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userRepository.findAll().stream()
                .map(userDTOConverter::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }
} 