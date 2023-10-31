package com.socialnetwork.messenger.message;

import java.util.Date;

public record MessageDTO(
    Long id,
    String text,
    String images,
    Date createDate,
    Boolean isEdited,
    Long creatorId,
    Long parentMessageId
){}
