package com.ANieto.api.dao;

import com.ANieto.api.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionDao extends JpaRepository<Region,Integer> {
}
