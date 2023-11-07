package com.socialnetwork.user;

import java.util.Date;

public record UserUpdateDTO(
    String name,
    String surname,
    Date birthday,
    String email,
    String password
){}
