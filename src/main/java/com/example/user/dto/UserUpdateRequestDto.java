package com.example.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class UserUpdateRequestDto {
  @Min(1)
  @NotNull
  @JsonProperty("user_id")
  private Long id;
  @NotBlank
  @JsonProperty("user_name")
  private String name;
  @Min(1)
  @JsonProperty("user_age")
  private int age;
}
