package com.juanprato.mytouch.controller;

import com.juanprato.mytouch.common.annotations.RequestToken;
import com.juanprato.mytouch.common.models.Response;
import com.juanprato.mytouch.dto.RegisterDTO;
import com.juanprato.mytouch.dto.UserDTO;
import com.juanprato.mytouch.exception.BadRequestException;
import com.juanprato.mytouch.service.FirebaseService;
import com.juanprato.mytouch.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final FirebaseService firebaseService;

    @Autowired
    public UserController(UserService userService, FirebaseService firebaseService) {
        this.userService = userService;
        this.firebaseService = firebaseService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response<UserDTO>> registerUser(@RequestBody RegisterDTO userDTO) {

        UserDTO user;
        try {
            user = this.userService.createOne(userDTO);
        } catch (BadRequestException ex) {
            logger.error("EXCEPTION => " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(new Response<>(ex));
        } catch (Exception ex) {
            logger.error("EXCEPTION => " + ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body(new Response<>(ex));
        }
        return ResponseEntity.ok(new Response<>(user));
    }

    @PostMapping("change-profile-picture")
    public ResponseEntity<?> changeProfilePicture(@RequestToken String token, @RequestParam("image")MultipartFile multipartFile) {
        UserDTO user;
        try {
            user = userService.updateProfileImage(token, multipartFile);
        } catch (BadRequestException e) {
            logger.error("EXCEPTION => ", e);
            return ResponseEntity.badRequest().body(new Response<>(e));
        } catch (Exception e) {
            logger.error("EXCEPTION => ", e);
            return ResponseEntity.internalServerError().body(new Response<>(e));
        }
        return ResponseEntity.ok(new Response<>(user));
    }

}
