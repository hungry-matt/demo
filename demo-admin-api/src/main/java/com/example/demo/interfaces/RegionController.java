package com.example.demo.interfaces;

import com.example.demo.application.RegionService;
import com.example.demo.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RegionController {

    @Autowired
    private RegionService resionService;

    @GetMapping("/regions")
    public List<Region> list() {
        List<Region> regions = resionService.getRegions();

        return regions;
    }
}
