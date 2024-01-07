package com.socialnetwork.controller;

import com.socialnetwork.dto.UserDTO;
import com.socialnetwork.dto.UserRegistrationDTO;
import com.socialnetwork.service.UserService;
import com.socialnetwork.dto.UserUpdateDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user/")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public void create(@RequestBody UserRegistrationDTO newUser){
        userService.createUser(newUser);
    }

    @PutMapping("/update/{userId}")
    public void update(
        @PathVariable("userId") Long id,
        @RequestBody UserUpdateDTO updateUser
    ){
        userService.updateUser(id, updateUser);
    }

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable("userId") Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/get-user/{userId}")
    public UserDTO getUser(@PathVariable("userId") Long id) throws IOException {
        return userService.getUser(id);
    }

    @GetMapping("/get-users")
    public List<UserDTO> getUsersPage(
        @RequestParam("page") int page,
        @RequestParam("size") int size
    ){
        return userService.getUsersPage(page, size);
    }

    @PutMapping("/upload-profile-image/{userId}")
    public void uploadProfileImage(
        @PathVariable("userId") Long userId,
        @RequestPart("image") MultipartFile image
    ) throws IOException
    {
        userService.uploadUserProfileImage(userId, image);
    }

    @GetMapping("/get-profile-image/{userId}")
    public ResponseEntity<Resource> getProfileImage(@PathVariable("userId") Long userId) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(
                new ByteArrayResource(userService.getUserProfileImage(userId)),
                headers,
                HttpStatus.OK
        );
    }
}
