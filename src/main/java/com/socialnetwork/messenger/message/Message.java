package com.socialnetwork.messenger.message;

import java.util.Date;

import com.socialnetwork.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_message")
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private String images;
    @NonNull
    private Date createDate;
    private Boolean isEdited;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;
    @ManyToOne
    @JoinColumn(name = "parent_message_id")
    private Message parentMessage; 
}
