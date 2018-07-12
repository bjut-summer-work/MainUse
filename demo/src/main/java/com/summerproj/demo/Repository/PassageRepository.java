 package com.summerproj.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.summerproj.demo.Entity.Passage;

import java.util.List;

public interface PassageRepository extends JpaRepository<Passage,Integer>{
    List<Passage> findAllByTitleContainingAndRoleIsGreaterThanEqual(String title, Integer lowrole);
    List<Passage> findAllByTitleContainingAndTypeIsAndRoleIsGreaterThanEqual(String title, Integer type, Integer lowrole);
    List<Passage> findAllByAuthor(String Author);
    List<Passage> findAllByRoleLessThanEqualOrderByRole(Integer maxrole);
}
