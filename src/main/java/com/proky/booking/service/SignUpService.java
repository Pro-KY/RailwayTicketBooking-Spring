package com.proky.booking.service;

import com.proky.booking.dto.UserDto;
import com.proky.booking.exception.ServiceException;
import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.dao.IUserTypeDao;
import com.proky.booking.persistence.entities.User;
import com.proky.booking.persistence.entities.Role;
import com.proky.booking.util.MessageSourceWrapper;
import com.proky.booking.util.PasswordEncryptor;
import com.proky.booking.util.constans.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log4j2
@Transactional
@Service
@AllArgsConstructor
public class SignUpService {
    private IUserDao userDao;
    private IUserTypeDao userTypeDao;
    private PasswordEncryptor passwordEncryptor;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ModelMapper modelMapper;
    private MessageSourceWrapper messageSourceWrapper;

    public void signUp(final UserDto userFromRequest) {
        final Optional<User> foundUser = userDao.findByEmail(userFromRequest.getEmail());
        if (foundUser.isPresent()) {
            throw new ServiceException(messageSourceWrapper.getMessage("user.exist.msg"));
        } else {
            final String encryptedPassword = bCryptPasswordEncoder.encode(userFromRequest.getPassword());
            final Role role = userTypeDao
                    .findByType(RoleEnum.USER.role)
                    .orElseThrow(() -> new ServiceException(messageSourceWrapper.getMessage("error.notfound")));

            final User newUser = modelMapper.map(userFromRequest, User.class);

            log.info(newUser);
            newUser.setRole(role);
            newUser.setPassword(encryptedPassword);
            userDao.save(newUser);
        }
    }
}
