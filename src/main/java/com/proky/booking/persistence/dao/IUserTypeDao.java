package com.proky.booking.persistence.dao;

import com.proky.booking.persistence.entities.Role;

import java.util.Optional;

public interface IUserTypeDao extends IDao<Role> {
    Optional<Role> findByType(String type);
}
