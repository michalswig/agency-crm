package com.michalswig.agencycrm.modules.identity.repository;

import com.michalswig.agencycrm.modules.identity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}