package com.juanprato.mytouch.controller;

import com.juanprato.mytouch.auth.Token;
import com.juanprato.mytouch.common.models.Response;
import com.juanprato.mytouch.dto.LoginDTO;
import com.juanprato.mytouch.exception.BadRequestException;
import com.juanprato.mytouch.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Token token;
        try {
            token = this.authService.login(loginDTO);
        } catch (BadRequestException ex) {
            logger.error("EXCEPTION => ", ex);
            return ResponseEntity.badRequest().body(new Response<>(ex));
        } catch (Exception ex) {
            logger.error("EXCEPTION => ", ex);
            return ResponseEntity.internalServerError().body(new Response<>(ex));
        }
        return ResponseEntity.ok(new Response<>(token));
    }

}
