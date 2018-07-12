package com.summerproj.demo.Repository;

import com.summerproj.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    //通过U_name来查询
    Optional<User> findByNameContains(String name);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameContains(String username);
}
