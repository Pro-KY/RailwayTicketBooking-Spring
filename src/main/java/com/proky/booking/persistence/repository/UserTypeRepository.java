package com.proky.booking.persistence.repository;

import com.proky.booking.persistence.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTypeRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
