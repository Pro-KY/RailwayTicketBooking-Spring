package com.proky.booking.persistence.dao;


import com.proky.booking.persistence.entities.User;
import com.proky.booking.persistence.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserDao extends IDao<User> {
    Optional<User> findByEmail(String email);
    Page<User> findAllByType(Role role, Pageable pageable);
}
