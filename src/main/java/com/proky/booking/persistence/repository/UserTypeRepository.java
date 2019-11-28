package com.proky.booking.persistence.repository;

import com.proky.booking.persistence.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    Optional<UserType> findByType(String type);
}
