package com.summerproj.demo.Repository;

import com.summerproj.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    //通过U_name来查询
    List<User> findAllByNameContaining(String name);
    Optional<User> findByUsername(String username);
    List<User> findAllByUsernameContaining(String username);
    List<User> findAllByRoleLessThanEqualOrderByRole(Integer maxrole);

}
