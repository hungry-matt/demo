package com.example.demo.application;

import com.example.demo.domain.Region;
import com.example.demo.domain.RegionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RegionServiceTest {

    @Mock
    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @Before
    public void setUp() {
        regionService = new RegionService(regionRepository);

        List<Region> regions = new ArrayList<>();

        regions.add(Region.builder()
                .name("Seoul")
                .build());

        given(regionService.getRegions()).willReturn(regions);
    }

    @Test
    public void getRegions() {
        List<Region> regions = regionService.getRegions();

        Region region = regions.get(0);

        assertThat(region.getName(), is("Seoul"));
    }

    @Test
    public void addRegion() {
        Region region = regionService.addRegion("Seoul");

        verify(regionRepository).save(any());

        assertThat(region.getName(), is("Seoul"));
    }
}