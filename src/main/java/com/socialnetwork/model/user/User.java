package com.socialnetwork.model.user;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_user")
public class User {
    @Id    
    @GeneratedValue
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
