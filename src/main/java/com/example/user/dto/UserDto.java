package com.example.user.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
  private Long id;
  private String name;
  private int age;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
