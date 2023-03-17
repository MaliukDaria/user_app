package com.example.user.mapper;

import com.example.user.dto.UserCreateRequestDto;
import com.example.user.dto.UserDto;
import com.example.user.dto.UserResponseDto;
import com.example.user.dto.UserUpdateRequestDto;
import com.example.user.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
  UserDto mapToDto(UserCreateRequestDto requestDto);

  UserDto mapToDto(UserUpdateRequestDto requestDto);

  UserDto mapToDto(User userDto);

  User mapToEntity(UserDto userDto);

  UserResponseDto mapToResponseDto(UserDto userDto);

  void updateUserFromDto(UserDto dto, @MappingTarget User entity);
}
