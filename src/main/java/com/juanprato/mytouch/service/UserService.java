package com.juanprato.mytouch.service;

import com.juanprato.mytouch.dto.RegisterDTO;
import com.juanprato.mytouch.dto.UserDTO;
import com.juanprato.mytouch.exception.AlreadyExistsException;
import com.juanprato.mytouch.exception.BadRequestException;
import com.juanprato.mytouch.exception.InternalServerException;
import com.juanprato.mytouch.mapper.UserMapper;
import com.juanprato.mytouch.model.User;
import com.juanprato.mytouch.repository.UserRepository;
import com.juanprato.mytouch.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final FirebaseService firebaseService;
    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Value("${files.max_size}")
    private Long maxSizeFiles;

    @Autowired
    public UserService(FirebaseService firebaseService, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.firebaseService = firebaseService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public UserDTO createOne(RegisterDTO userDTO) throws BadRequestException, AlreadyExistsException {
        if (userDTO.getUsername() == null || userDTO.getPassword() == null) {
            throw new BadRequestException("Invalid username or password");
        }
        User userToSave = this.userMapper.registerDTOToUser(userDTO);
        if (this.userRepository.existsByUsername(userToSave.getUsername())) {
            throw new AlreadyExistsException("User with that username already exitst");
        }
        userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
        User userSaved = this.userRepository.save(userToSave);
        return this.userMapper.userToUserDTO(userSaved);
    }

    public User getOneByUsername(String username) {
        return this.userRepository.getByUsername(username);
    }

    public User getOneById(Long id) {
        return this.userRepository.getById(id);
    }

    public UserDTO updateProfileImage(String token, MultipartFile multipartFile) throws BadRequestException, InternalServerException, AlreadyExistsException {

        UserDTO userResponse;
        try {
            if (!validFile(multipartFile)) {
                throw new BadRequestException("Invalid file");
            }
            String username = jwtUtil.extractUsername(token);
            User user = this.userRepository.getByUsername(username);
            String url = this.firebaseService.uploadFile(String.format("%s/profileImage", user.getUsername()), multipartFile);
            user.setProfile_image(url);
            User usarSaved = this.userRepository.save(user);
            userResponse = this.userMapper.userToUserDTO(usarSaved);

        } catch (BadRequestException | AlreadyExistsException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException("Unkown error");
        }
        return userResponse;
    }

    // TODO: Return list errors with the file
    private Boolean validFile(MultipartFile multipartFile) {

        if (multipartFile.getSize() > this.maxSizeFiles) {
            return false;
        }

        List<String> validTypes = new ArrayList<>();
        validTypes.add("image/jpge");
        validTypes.add("image/jpg");
        validTypes.add("image/png");

        String contentType = multipartFile.getContentType();

        return validTypes.stream().anyMatch(v -> v.equals(contentType));
    }

}
