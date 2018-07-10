 package com.summerproj.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.summerproj.demo.Entity.Passage;
import java.util.Optional;

public interface PassageRepository extends JpaRepository<Passage,Integer>{
    Optional<Passage> findByTitleContains(String title);
    Optional<Passage> findByAuthor(String Author);
}
