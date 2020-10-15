package com.example.demo.application;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void getUsers() {
        List<User> users = new ArrayList<>();

        users.add(User.builder()
                .name("test")
                .email("test@example.com")
                .level(1L)
                .build());

        given(userRepository.findAll()).willReturn(users);

        User user = users.get(0);

        assertThat(user.getName()).isEqualTo("test");
    }
}