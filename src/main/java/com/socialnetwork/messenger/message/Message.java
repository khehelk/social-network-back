package com.socialnetwork.messenger.message;

import com.socialnetwork.user.User;
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
@Table(name="tbl_message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String images;
    @NonNull
    private Date createDate;
    @NonNull
    private Boolean isEdited;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User creator;
    @ManyToOne
    @JoinColumn(name = "parent_message_id")
    private Message parentMessage; 
}