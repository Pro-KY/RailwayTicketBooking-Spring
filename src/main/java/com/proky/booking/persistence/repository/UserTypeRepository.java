package com.proky.booking.persistence.repository;

import com.proky.booking.persistence.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
}
