package com.badminton.converter;

import com.badminton.dto.UserDTO;
import com.badminton.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter {
    
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }
    
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        return user;
    }
} 