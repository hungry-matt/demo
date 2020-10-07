package com.example.demo.application;

import com.example.demo.domain.Region;
import com.example.demo.domain.RegionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RegionServiceTest {

    @Mock
    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @Before
    public void setUp() {
        regionService = new RegionService(regionRepository);
    }

    @Test
    public void getRegions() {
        List<Region> regions = regionService.getRegions();

        Region region = regions.get(0);

        assertThat(region.getName(), is("Seoul"));
    }
}