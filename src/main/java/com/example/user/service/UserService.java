package com.example.user.service;

import com.example.user.dto.UserDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface UserService {
  UserDto save(UserDto user);

  UserDto update(UserDto user);

  boolean delete(Long userId);

  UserDto getById(Long id);

  List<UserDto> getAll(Pageable pageable);
}
