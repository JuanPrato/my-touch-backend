package com.juanprato.mytouch.mapper;

import com.juanprato.mytouch.dto.FriendDTO;
import com.juanprato.mytouch.model.Friend;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FriendMapper {

    FriendMapper INSTANCE = Mappers.getMapper(FriendMapper.class);


    FriendDTO friendToFriendDTO(Friend friend);

    Friend friendDTOToFriend(FriendDTO friendDTO);

}
