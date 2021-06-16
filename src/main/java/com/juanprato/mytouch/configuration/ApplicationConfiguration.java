package com.juanprato.mytouch.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {

    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return this.bcrypt;
    }

}
