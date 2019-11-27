package com.proky.booking.persistence.dao;

import com.proky.booking.persistence.entities.UserType;

import java.util.Optional;

public interface IUserTypeDao extends IDao<UserType> {
    Optional<UserType> findByType(String type);
}
