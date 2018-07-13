package com.summerproj.demo.Repository;

import com.summerproj.demo.Entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendRepository extends JpaRepository<Recommend,Integer> {
    Optional <Recommend> findByStartAndEndAndEndtel(String start, String end, String endtel);
}
