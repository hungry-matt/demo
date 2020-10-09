package com.example.demo.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RegionTest {

    @Test
    public void cretaion() {
        Region region = Region.builder()
                .name("서울")
                .build();

        assertThat(region.getName(), is("서울"));
    }
}