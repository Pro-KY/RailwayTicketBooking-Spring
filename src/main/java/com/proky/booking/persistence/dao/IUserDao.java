package com.proky.booking.persistence.dao;


import com.proky.booking.persistence.entities.User;
import com.proky.booking.persistence.entities.UserType;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface IUserDao extends IDao<User> {
    Optional<User> findByEmail(String email);
    Page<User> findAllByType(UserType userType, Pageable pageable);
}
