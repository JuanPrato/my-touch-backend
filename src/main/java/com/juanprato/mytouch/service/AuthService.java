package com.juanprato.mytouch.service;

import com.juanprato.mytouch.auth.Token;
import com.juanprato.mytouch.dto.LoginDTO;
import com.juanprato.mytouch.dto.UserDTO;
import com.juanprato.mytouch.exception.BadRequestException;
import com.juanprato.mytouch.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserService userService ,JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public Token login(LoginDTO loginDTO) throws BadRequestException {
        Token token = new Token();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );
            final UserDetails userDetails = userService.loadUserByUsername(loginDTO.getUsername());

            String tokenString = jwtUtil.generateToken(userDetails);
            token.setToken(tokenString);
            token.setExpiration(jwtUtil.extractExpiration(tokenString));
        } catch (BadCredentialsException | UsernameNotFoundException ex) {
            throw new BadRequestException("Invalid credentials");
        }
        return token;
    }

}
