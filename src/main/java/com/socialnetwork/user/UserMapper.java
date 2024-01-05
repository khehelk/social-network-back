package com.socialnetwork.user;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDTO toDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPassword(),
                user.getBirthday(),
                user.getCreateDate()
        );
    }
}
