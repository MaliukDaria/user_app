package com.example.user.service.impl;

import com.example.user.dto.UserDto;
import com.example.user.exception.EntityNotFoundException;
import com.example.user.mapper.UserMapper;
import com.example.user.model.entity.User;
import com.example.user.repository.UserRepository;
import com.example.user.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public UserDto save(UserDto user) {
    UserDto userDto = userMapper.mapToDto(userRepository.save(userMapper.mapToEntity(user)));
    log.info("New user was created {}", userDto);
    return userDto;
  }

  @Override
  public UserDto update(UserDto userDto) {
    User user = getUserEntityById(userDto.getId());
    userMapper.updateUserFromDto(userDto, user);
    UserDto updatedUser = userMapper.mapToDto(userRepository.save(user));
    log.info("User was updated {}", updatedUser);
    return updatedUser;
  }

  @Override
  public boolean delete(Long userId) {
    userRepository.delete(getUserEntityById(userId));
    log.info("User was deleted, user id: {}", userId);
    return true;
  }

  @Override
  public UserDto getById(Long id) {
    return userMapper.mapToDto(getUserEntityById(id));
  }

  @Override
  public List<UserDto> getAll(Pageable pageable) {
    return userRepository.findAll(pageable).stream()
        .map(userMapper::mapToDto)
        .collect(Collectors.toList());
  }

  private User getUserEntityById(Long id) {
    return userRepository.findById(id).orElseThrow(
        () -> new EntityNotFoundException("There is no user with id " + id));
  }
}
