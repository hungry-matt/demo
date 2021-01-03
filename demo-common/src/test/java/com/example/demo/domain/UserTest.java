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
        assertThat(user.isActive(), is(true));

        user.deactivate();

        assertThat(user.isActive(), is(false));
    }

    @Test
    public void getRestaurantOwner() {
        //사용자가 레스토랑 주인인지 확인

        User user = User.builder()
                .level(1L)
                .build();

        //권한 level 확인
        assertThat(user.isRestaurantOwner(), is(false));

        //권한 및 레스토랑 아이디 부여
        user.setRestaurantId(1004L);

        assertThat(user.isRestaurantOwner(), is(true));
        assertThat(user.getRestaurantId(), is(1004L));
    }
}
