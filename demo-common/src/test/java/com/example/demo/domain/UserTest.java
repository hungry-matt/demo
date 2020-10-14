package com.example.demo.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void creation() {
        User user = User.builder()
                .name("테스터")
                .email("tester@example.com")
                .level(100L)
                .build();

        assertThat(user.getName(), is("테스터"));
        assertThat(user.isAdmin(), is(true));
    }

}