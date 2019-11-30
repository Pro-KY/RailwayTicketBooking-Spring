package com.proky.booking.service;

import com.proky.booking.dto.UserDto;
import com.proky.booking.exception.ServiceException;
import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.dao.IUserTypeDao;
import com.proky.booking.persistence.entities.User;
import com.proky.booking.persistence.entities.UserType;
import com.proky.booking.util.PasswordEncryptor;
import com.proky.booking.util.constans.enums.UserTypeEnum;
import com.proky.booking.util.properties.Message;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
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
    private Message message;
    private ModelMapper modelMapper;

    public void signUp(final UserDto userFromRequest) {

        final Optional<User> foundUser = userDao.findByEmail(userFromRequest.getEmail());
        if (foundUser.isPresent()) {
            throw new ServiceException(message.userExist);
        } else {
            final String encryptedPassword = passwordEncryptor.encrypt(userFromRequest.getPassword());
            final UserType userType = userTypeDao.findByType(UserTypeEnum.USER.type).orElseThrow(() -> new ServiceException(message.notFoundEntity));
            userFromRequest.setPassword(encryptedPassword);

            final User newUser = modelMapper.map(userFromRequest, User.class);
            log.info(newUser);
            newUser.setUserType(userType);
            userDao.save(newUser);
        }
    }

}
