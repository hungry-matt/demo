package com.example.demo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RegionRepository extends CrudRepository<Region, Long> {
    List<Region> findAll();
}
