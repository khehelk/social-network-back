package com.socialnetwork.dto;

import java.util.Date;

public record UserDTO(
    Long id,
    String name,
    String surname,
    String email,
    String password,
    Date birthday,
    Date createDate
){}
