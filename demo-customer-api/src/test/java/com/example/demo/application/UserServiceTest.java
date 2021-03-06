package com.example.demo.application;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        userService = new UserService(userRepository, passwordEncoder);
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

        verify(userRepository).save(any());

        assertThat(user.getName(), is(name));
    }

    @Test(expected = EmailExistedException.class)
    public void registerUserWithExistedEmail() {
        String name = "tester";
        String email = "tester@test.com";
        String password = "test";

        User mockUser = User.builder()
                .id(1004L)
                .name(name)
                .email(email)
                .password(password)
                .build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        userService.registerUser(name, email, password);

        verify(userRepository, never()).save(any());
    }

}