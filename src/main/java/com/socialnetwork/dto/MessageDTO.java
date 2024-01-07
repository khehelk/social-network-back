package com.socialnetwork.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public record MessageDTO(
    Long id,
    String text,
    List<MultipartFile> images,
    Date createDate,
    Boolean isEdited,
    Long creatorId,
    Long parentMessageId
){}
