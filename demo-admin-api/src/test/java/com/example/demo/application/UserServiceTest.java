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
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    public void getUsers() {
        List<User> mockUsers = new ArrayList<>();

        mockUsers.add(User.builder()
                .name("test")
                .email("test@example.com")
                .level(1L)
                .build());

        given(userRepository.findAll()).willReturn(mockUsers);

        List<User> users = userService.getUsers();

        User user = users.get(0);

        assertThat(user.getName(), is("test"));
    }

    @Test
    public void addUser() {
        String name = "admin";
        String email = "admin@example.com";

        User mockUser = User.builder().name(name).email(email).build();

        given(userRepository.save(any())).willReturn(mockUser);

        User user = userService.addUser(name, email);

        assertThat(user.getName(), is(name));
    }

    @Test
    public void updateUser() {
        Long id = 1004L;
        String name = "admin";
        String email = "admin@example.com";
        Long level = 100L;

        User mockUser = User.builder()
                .id(id)
                .name("user")
                .email(email)
                .level(1L)
                .build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.updateUser(id, name, email, level);

        verify(userRepository).findById(eq(id));

        assertThat(user.getName(), is(name));
        assertThat(user.isAdmin(), is(true));

    }

    @Test
    public void deactivateUser() {
        Long id = 1004L;
        String email = "admin@example.com";
        Long level = 100L;

        User mockUser = User.builder()
                .id(id)
                .name("user")
                .email(email)
                .level(level)
                .build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.deactivateUser(id);

        verify(userRepository).findById(id);

        assertThat(user.isAdmin(), is(false));
        assertThat(user.isActive(), is(false));
    }
}