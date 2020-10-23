package com.example.demo.application;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    public void registerUser() {
        String name = "tester";
        String email = "tester@test.com";
        String password = "test";

        User mockUser = User.builder()
                .id(1004L)
                .name(name)
                .email(email)
                .password(password)
                .build();

        given(userRepository.save(any())).willReturn(mockUser);

        User user = userService.registerUser(name, email, password);

        assertThat(user.getName(), is(name));
    }
}