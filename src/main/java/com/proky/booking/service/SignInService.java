package com.proky.booking.service;

import com.proky.booking.dto.UserDto;
import com.proky.booking.exception.ServiceException;
import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.entities.User;
import com.proky.booking.util.PasswordEncryptor;
import com.proky.booking.util.properties.MessageProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class SignInService {
    private IUserDao userDao;
    private PasswordEncryptor passwordEncryptor;
    private MessageProperties messageProperties;

    public User signIn(final UserDto userDto) {
        final Optional<User> foundUser = userDao.findByEmail(userDto.getEmail());

        return foundUser.filter(user -> {
            final String enteredPassword = userDto.getPassword();
            final String encryptedPassword = passwordEncryptor.encrypt(enteredPassword);
            return encryptedPassword.equals(user.getPassword());
        }).orElseThrow(() -> new ServiceException(messageProperties.authorizationError));
    }

}
