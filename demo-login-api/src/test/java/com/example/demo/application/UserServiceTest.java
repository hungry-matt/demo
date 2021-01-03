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
    public void authenticateWithValidAttributes() {
        String email = "test@test.com";
        String password = "test";

        User mockUser = User.builder().email(email).build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(), any())).willReturn(true);

        User user = userService.authenticate(email, password);

        //email check
        assertThat(user.getEmail(), is(email));
    }

    @Test(expected = EmailNotExistedException.class)
    public void authenticateWithNotExistedEmail() {
        //email 인증이 실패하는 경우
        String email = "x@test.com";
        String password = "test";

        given(userRepository.findByEmail(email)).willReturn(Optional.empty());

        userService.authenticate(email, password);
    }

    @Test(expected = PasswordWrongException.class)
    public void authenticateWithWrongPassword() {
        //password 인증이 실패하는 경우
        String email = "test@test.com";
        String password = "x";

        User mockUser = User.builder().email(email).build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(), any())).willReturn(false);

        userService.authenticate(email, password);
    }
}