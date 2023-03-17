package com.example.user;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.user.dto.UserCreateRequestDto;
import com.example.user.dto.UserDto;
import com.example.user.dto.UserResponseDto;
import com.example.user.service.UserService;
import com.example.user.util.FileUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private UserService userService;

  @Test
  public void create_validUser_ok() throws Exception {
    String testData = FileUtil.readFromFileToString("/data/valid_user.json");
    UserCreateRequestDto testUser = new ObjectMapper()
        .readValue(testData, UserCreateRequestDto.class);
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
        .post("/users")
        .content(testData)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    UserResponseDto actualUser = new ObjectMapper()
        .readValue(mvcResult.getResponse().getContentAsString(), UserResponseDto.class);
    Assertions.assertEquals(actualUser.getAge(), testUser.getAge());
    Assertions.assertEquals(actualUser.getName(), testUser.getName());
    Assertions.assertNotNull(actualUser.getId());
    UserDto dbUser = userService.getById(actualUser.getId());
    Assertions.assertNotNull(dbUser);
    Assertions.assertEquals(dbUser.getAge(), testUser.getAge());
    Assertions.assertEquals(dbUser.getName(), testUser.getName());
  }
}
