package com.example.user.controller;

import com.example.user.dto.UserCreateRequestDto;
import com.example.user.dto.UserResponseDto;
import com.example.user.dto.UserUpdateRequestDto;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private static final String DEFAULT_OFFSET = "0";
  private static final String PAGE_SIZE = "10";
  private final UserMapper userMapper;
  private final UserService userService;

  @PostMapping
  ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserCreateRequestDto requestDto) {
    UserResponseDto userResponseDto = userMapper.mapToResponseDto(
        userService.save(
            userMapper.mapToDto(requestDto)
        )
    );
    return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
  }

  @PatchMapping
  ResponseEntity<UserResponseDto> update(@RequestBody @Valid UserUpdateRequestDto requestDto) {
    UserResponseDto userResponseDto = userMapper.mapToResponseDto(
        userService.update(
            userMapper.mapToDto(requestDto)
        )
    );
    return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<Boolean> delete(@PathVariable Long id) {
    userService.delete(id);
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
    UserResponseDto userResponseDto = userMapper.mapToResponseDto(userService.getById(id));
    return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
  }

  @GetMapping
  ResponseEntity<List<UserResponseDto>> getAll(
      @RequestParam(required = false, defaultValue = DEFAULT_OFFSET) int offset,
      @RequestParam(required = false, defaultValue = PAGE_SIZE) int limit) {
    Pageable pageable = PageRequest.of(offset, limit);
    List<UserResponseDto> userResponseDtos = userService.getAll(pageable).stream()
        .map(userMapper::mapToResponseDto)
        .collect(Collectors.toList());
    return new ResponseEntity<>(userResponseDtos, HttpStatus.OK);
  }
}
