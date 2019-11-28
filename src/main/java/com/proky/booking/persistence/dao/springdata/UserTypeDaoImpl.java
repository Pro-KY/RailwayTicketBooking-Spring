package com.proky.booking.persistence.dao.springdata;

import com.proky.booking.persistence.dao.IUserTypeDao;
import com.proky.booking.persistence.entities.UserType;
import com.proky.booking.persistence.repository.UserTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class UserTypeDaoImpl implements IUserTypeDao {
    private UserTypeRepository userTypeRepository;

    @Override
    public Optional<UserType> findByType(String type) {
        return userTypeRepository.findByType(type);
    }

    @Override
    public UserType save(UserType entity) {
        return userTypeRepository.save(entity);
    }

    @Override
    public UserType update(UserType entity) {
        return userTypeRepository.save(entity);
    }

    @Override
    public void delete(UserType entity) {
        userTypeRepository.delete(entity);
    }

    @Override
    public Optional<UserType> findById(Long id) {
        return userTypeRepository.findById(id);
    }
}
