package com.example.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserCreateRequestDto {
  @NotBlank
  @JsonProperty("user_name")
  private String name;
  @Min(1)
  @JsonProperty("user_age")
  private int age;
}
