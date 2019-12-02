package com.proky.booking.persistence.repository;

import com.proky.booking.persistence.entities.User;
import com.proky.booking.persistence.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Page<User> findAllByRole(Role role, Pageable pageable);
}
