package com.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.user.dto.UserRequest;
import com.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    public UserRequest toDto(User user);

    @Mapping(target = "id", ignore = true)
    public User toEntity(UserRequest userRequest);
}
