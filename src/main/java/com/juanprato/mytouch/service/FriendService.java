package com.juanprato.mytouch.service;

import com.juanprato.mytouch.dto.FriendDTO;
import com.juanprato.mytouch.dto.UserDTO;
import com.juanprato.mytouch.exception.AlreadyExistsException;
import com.juanprato.mytouch.exception.BadRequestException;
import com.juanprato.mytouch.exception.NotFoundException;
import com.juanprato.mytouch.mapper.FriendMapper;
import com.juanprato.mytouch.model.Friend;
import com.juanprato.mytouch.model.User;
import com.juanprato.mytouch.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {

    private final UserService userService;
    private final FriendRepository friendRepository;
    private final FriendMapper friendMapper = FriendMapper.INSTANCE;

    @Autowired
    public FriendService (UserService userService, FriendRepository friendRepository) {
        this.userService = userService;
        this.friendRepository = friendRepository;
    }

    public FriendDTO addFriend(String username, Long friendId) throws NotFoundException, BadRequestException, AlreadyExistsException {

        User user = userService.getOneByUsername(username);
        User friend = userService.getOneById(friendId);

        if (user == null || friend == null) {
            throw new NotFoundException("User or friend not found");
        }

        if (user.getId().equals(friend.getId())) {
            throw new BadRequestException("You can't yourself");
        }

        if (this.friendRepository.existsByFriend1AndFriend2(user.getId(), friend.getId()) ||
            this.friendRepository.existsByFriend1AndFriend2(friend.getId(), user.getId())) {
            throw new AlreadyExistsException("Friendship already exists");
        }

        Friend friendToSave = createFriend(user, friend);

        Friend friendSaved = this.friendRepository.save(friendToSave);

        return this.friendMapper.friendToFriendDTO(friendSaved);
    }

    private Friend createFriend(User user, User friend) {
        Friend response = new Friend(user, friend);
        response.setFriend1(user);
        response.setFriend2(friend);
        return response;
    }
}
