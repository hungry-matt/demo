package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

public class JwtUtilTest {

    private static final String SECRET = "12345678901234567890123456789012";

    private JwtUtil jwtUtil;

    @Before
    public void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    public void crateToken() {
        //아이디와 이름으로 토큰 생성
        String token = jwtUtil.createToken(1004L, "John", null);

        assertThat(token, containsString("."));
    }

    @Test
    public void getClaims() {

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAwNCwibmFtZSI6IkpvaG4ifQ.e7Di1AdEZKKnbrMqLxdXChp9ds35Tqn5OFmsxMY1uE8";

        Claims claims = jwtUtil.getClaims(token);

        assertThat(claims.get("id", Long.class), is(1004L));
        assertThat(claims.get("name"), is("John"));
    }
}