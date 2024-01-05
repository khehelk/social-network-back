package com.socialnetwork.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    @Value("${myapp.storagePath}")
    private String storagePath;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public User createUser(UserRegistrationDTO userRegistrationDTO){
        String email = userRegistrationDTO.email();
        if (userRepository.existsUserByEmail(email)){
            //TODO: throw exception
            return null;
        }

        User newUser = new User(
            null,
            userRegistrationDTO.name(),
            userRegistrationDTO.surname(),
            userRegistrationDTO.birthday(),
            userRegistrationDTO.email(),
            userRegistrationDTO.password(),
            "",
            userRegistrationDTO.createDate()
        );

        return userRepository.save(newUser);
    }

    @Transactional
    public User updateUser(
        Long userId,
        UserUpdateDTO userUpdate
    ){
        User user = userRepository.getReferenceById(userId);  
        user.setSurname(userUpdate.surname());      
        if (userUpdate.name() != null) 
            user.setName(userUpdate.name());        
        if (userUpdate.birthday() != null) 
            user.setBirthday(userUpdate.birthday());
        if (userUpdate.email() != null) 
            user.setEmail(userUpdate.email());
        if (userUpdate.password() != null)
            user.setPassword(userUpdate.password());        
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long userId){        
        userRepository.deleteById(userId);                             
    }

    @Transactional(readOnly = true)
    public UserDTO getUser(Long userId) throws IOException {
        User user = userRepository.getReferenceById(userId);
        return userMapper.toDTO(user);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getUsersPage(
        int page,
        int size
    ){
        return userRepository.findAll(
                    PageRequest.of(
                            page - 1, size,
                            Sort.by("id").ascending()))
                .getContent()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public User updateUserProfileImage(
        Long userId,
        String profileImageId
    ){
        User user = userRepository.getReferenceById(userId);
        user.setProfileImage(profileImageId);
        return userRepository.save(user);
    }
    
    public void uploadUserProfileImage(
        Long userId,
        MultipartFile file
    ) throws IOException
    {
        if (!isImage(file) || !userRepository.existsById(userId)) return; //TODO: ошибка, если файл не является изображением
        String profileImageId = UUID.randomUUID().toString();
        String profileImagePath = "%s/users/%s/images/".formatted(storagePath, userId);
        Path profileImageDirectory = Paths.get(profileImagePath);
        Path profileImage = Path.of(profileImagePath, profileImageId);
        try{
            if (!Files.exists(profileImageDirectory))
                Files.createDirectories(profileImageDirectory);
            else
                Files.walkFileTree(profileImageDirectory, 
                    new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }
        
                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {                    
                        return FileVisitResult.CONTINUE;
                    }
                });
            Files.copy(file.getInputStream(), profileImage);
        }catch(IOException ex){/*TODO: ошибка*/}
        updateUserProfileImage(userId, profileImageId);  
    }

    public byte[] getUserProfileImage(Long userId) throws IOException{
        User user = userRepository.getReferenceById(userId);
        if (user.getProfileImage().isEmpty()) return new byte[]{};
        String profileImageString = "%s/users/%s/images/%s".formatted(storagePath, user.getId(), user.getProfileImage());
        Path profileImagePath = Paths.get(profileImageString);                
        return Files.readAllBytes(profileImagePath);        
    }

    private static boolean isImage(MultipartFile file) throws IOException {
        try {
            ImageIO.read(file.getInputStream());
            return true;
        } catch (IOException e) {            
            return false;
        }
    }
}
