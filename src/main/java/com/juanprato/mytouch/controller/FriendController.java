package com.juanprato.mytouch.controller;

import com.juanprato.mytouch.common.annotations.RequestToken;
import com.juanprato.mytouch.common.models.Response;
import com.juanprato.mytouch.dto.FriendDTO;
import com.juanprato.mytouch.exception.AlreadyExistsException;
import com.juanprato.mytouch.exception.BadRequestException;
import com.juanprato.mytouch.exception.NotFoundException;
import com.juanprato.mytouch.service.FriendService;
import com.juanprato.mytouch.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
public class FriendController {

    private final FriendService friendService;
    private final JwtUtil jwtUtil;
    private final Logger logger = LoggerFactory.getLogger(FriendController.class);

    @Autowired
    public FriendController(FriendService friendService, JwtUtil jwtUtil) {
        this.friendService = friendService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/{friend-id}")
    public ResponseEntity<?> addFriend(@RequestToken String token, @PathVariable("friend-id") Long friendId) {
        FriendDTO friendship;
        try {
            friendship = this.friendService.addFriend(jwtUtil.extractUsername(token), friendId);
        } catch (AlreadyExistsException ex) {
            logger.error("EXCEPTION: ALREADY EXISTS => ", ex);
            return new ResponseEntity<>(new Response<>(ex), HttpStatus.CONFLICT);
        } catch (NotFoundException ex) {
            logger.error("EXCEPTION: NOT FOUND => ", ex);
            return new ResponseEntity<Response<?>>(new Response<>(ex), HttpStatus.NOT_FOUND);
        } catch (BadRequestException ex) {
            logger.error("EXCEPTION: BAD REQUEST => ", ex);
            return ResponseEntity.badRequest().body(new Response<>(ex));
        } catch (Exception ex) {
            logger.error("EXCEPTION: UNKNONW ERROR => ", ex);
            return ResponseEntity.internalServerError().body(new Response<>(ex));
        }
        return ResponseEntity.ok(new Response<>(friendship));
    }

    @DeleteMapping("/{friend-id}")
    public ResponseEntity<?> removeFriend(@RequestToken String token, @PathVariable("friend-id") Long friendId) {
        return null;
    }

}
