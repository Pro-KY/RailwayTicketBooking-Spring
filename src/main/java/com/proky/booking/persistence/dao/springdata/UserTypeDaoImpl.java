package com.proky.booking.persistence.dao.springdata;

import com.proky.booking.persistence.dao.IUserTypeDao;
import com.proky.booking.persistence.entities.Role;
import com.proky.booking.persistence.repository.UserTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class UserTypeDaoImpl implements IUserTypeDao {
    private UserTypeRepository userTypeRepository;

    @Override
    public Optional<Role> findByType(String type) {
        return userTypeRepository.findByName(type);
    }

    @Override
    public Role save(Role entity) {
        return userTypeRepository.save(entity);
    }

    @Override
    public Role update(Role entity) {
        return userTypeRepository.save(entity);
    }

    @Override
    public void delete(Role entity) {
        userTypeRepository.delete(entity);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return userTypeRepository.findById(id);
    }
}
