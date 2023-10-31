package com.socialnetwork.messenger.message;

import com.socialnetwork.messenger.chat.ChatUsers;
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
@Table(name="tbl_message_recipient")
public class MessageRecipient {
    @Id
    @GeneratedValue
    private Long id;
    private Boolean isReaded;
    @ManyToOne    
    @JoinColumn(name = "message_id")
    @NonNull
    private Message message;
    @ManyToOne
    @JoinColumn(name = "recepient_id")
    private User recipient;
    @ManyToOne
    @JoinColumn(name = "recepient_chat_id")
    private ChatUsers recipientChat;
}
