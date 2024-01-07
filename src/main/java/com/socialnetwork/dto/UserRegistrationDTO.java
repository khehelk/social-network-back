package com.socialnetwork.dto;

import java.util.Date;

public record UserRegistrationDTO(
    String name,
    String surname,
    Date birthday,
    String email,
    String password,
    Date createDate
){}
