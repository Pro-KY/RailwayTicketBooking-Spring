package com.proky.booking.service;

import com.proky.booking.dto.PageDto;
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
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Log4j2
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private IUserTypeDao userTypeDao;
    private IUserDao userDao;
    private Message message;
    private ModelMapper modelMapper;
    private PasswordEncryptor passwordEncryptor;

    public boolean isAdministrator(User authenticatedUser) {
        final UserType userType = authenticatedUser.getUserType();

        final UserType adminUserType = userTypeDao
                .findByType(UserTypeEnum.ADMIN.type)
                .orElseThrow(() -> new ServiceException(message.notFoundEntity));

        return userType.equals(adminUserType);
    }

    @Transactional
    public void updateUser(UserDto userDto) {
        final User user = findUserById(Long.parseLong(userDto.getId()));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        if (isNewPasswordPresent(userDto)) {
            final String encryptedPassword = passwordEncryptor.encrypt(userDto.getNewPassword());
            user.setPassword(encryptedPassword);
        }

        userDao.update(user);
    }

    private boolean isNewPasswordPresent(UserDto userDto) {
        final String newPassword = userDto.getNewPassword();
        return newPassword != null && !newPassword.isEmpty();
    }

    public PageDto findAllRegisteredUsers(PageDto pageDto) {

        final UserType userType = userTypeDao.
                findByType(UserTypeEnum.USER.type)
                .orElseThrow(() -> new ServiceException(message.notFoundEntity));

        final PaginationService paginationService = getProxyPaginationService();
        paginationService.setPageDto(pageDto);
        paginationService.calculatePageIndex();

        Pageable pageable = PageRequest.of(paginationService.getCurrentPageIndex(), paginationService.getPageSize());

        final Page<User> allUsersPage = userDao.findAllByType(userType, pageable);
        final List<UserDto> allUsersDto = allUsersPage.get().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        paginationService.setAllPagesAmount(allUsersPage.getTotalPages());
        paginationService.changeIndexBoundariesAndButtonsState();
        pageDto.setPageList(allUsersDto);
        paginationService.updatePageDto();


        return paginationService.getpageDto();
    }

    public User findUserById(Long id) {
        return userDao.findById(id).orElseThrow(() -> new ServiceException(message.notFoundEntity));
    }

    public UserDto getUserDtoById(Long id) {
        final User user = findUserById(id);
        return modelMapper.map(user, UserDto.class);
    }

    @Lookup
    public PaginationService getProxyPaginationService() {
        log.info("paginationService called");
        return null;
    }

}
