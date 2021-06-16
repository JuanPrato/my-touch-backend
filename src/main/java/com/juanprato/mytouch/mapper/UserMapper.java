package com.juanprato.mytouch.mapper;

import com.juanprato.mytouch.dto.RegisterDTO;
import com.juanprato.mytouch.dto.UserDTO;
import com.juanprato.mytouch.dto.UserWithoutFriendsDTO;
import com.juanprato.mytouch.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = FriendMapper.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User registerDTOToUser(RegisterDTO registerDTO);

    @Mapping(source = "profile_image", target = "profileImage")
    UserDTO userToUserDTO(User user);

    UserWithoutFriendsDTO userToUserWithoutFriendsDTO(User user);

}
