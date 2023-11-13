package com.digitalholics.securityservice.domain.persistence;


import com.digitalholics.securityservice.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    //Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
