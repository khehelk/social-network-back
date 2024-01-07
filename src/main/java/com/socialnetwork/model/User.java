package com.socialnetwork.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_user")
public class User {
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    private String surname;
    @NonNull
    private Date birthday;
    @NonNull    
    private String email;
    @NonNull
    private String password;
    private String profileImage;
    @NonNull
    private Date createDate;
}
