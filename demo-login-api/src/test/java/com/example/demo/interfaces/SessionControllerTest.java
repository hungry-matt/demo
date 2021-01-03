package com.example.demo.interfaces;

import com.example.demo.application.EmailNotExistedException;
import com.example.demo.application.PasswordWrongException;
import com.example.demo.application.UserService;
import com.example.demo.domain.User;
import com.example.demo.utils.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    public void createWithValidAttributes() throws Exception {
        Long id = 1004L;
        String name = "John";
        String email = "test@test.com";
        String password = "test";

        User mockUser = User.builder()
                .id(id)
                .name(name)
                .level(1L)
                .build();

        given(userService.authenticate(email, password)).willReturn(mockUser);

        given(jwtUtil.createToken(id, name, null)).willReturn("header.payload.signature");

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"tester\", \"email\":\"test@test.com\", \"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/session"))
                .andExpect(content().string(
                        containsString("{\"accessToken\":\"header.payload.signature\"}"))
                );

        verify(userService).authenticate(eq(email), eq(password));
    }

    @Test
    public void createWithWrongPassword() throws Exception {
        //password를 잘못 입력 했을경우
        given(userService.authenticate("test@test.com", "x"))
                .willThrow(PasswordWrongException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"tester\", \"email\":\"test@test.com\", \"password\":\"x\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("test@test.com"), eq("x"));
    }

    @Test
    public void createWithNotExistedEmail() throws Exception {
        given(userService.authenticate("x@test.com", "test"))
                .willThrow(EmailNotExistedException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"tester\", \"email\":\"x@test.com\", \"password\":\"test\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("x@test.com"), eq("test"));
    }

    @Test
    public void createRestaurantOwner() throws Exception {
        Long id = 1004L;
        String name = "John";
        String email = "test@test.com";
        String password = "test";
        Long resturantId = 300L;
        Long level = 50L;


        User mockUser = User.builder()
                .id(id)
                .name(name)
                .level(level)
                .restaurantId(resturantId)
                .build();

        given(userService.authenticate(email, password)).willReturn(mockUser);

        given(jwtUtil.createToken(id, name, resturantId)).willReturn("header.payload.signature");

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"tester\", \"email\":\"test@test.com\", \"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/session"))
                .andExpect(content().string(
                        containsString("{\"accessToken\":\"header.payload.signature\"}"))
                );

        verify(userService).authenticate(eq(email), eq(password));
    }
}