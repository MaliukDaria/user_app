package com.example.user.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BaseResponse<T> {
  private T value;
  private LocalDateTime timestamp;
}
