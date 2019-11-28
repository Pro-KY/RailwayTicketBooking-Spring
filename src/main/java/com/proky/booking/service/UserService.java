package com.proky.booking.service;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.TrainDto;
import com.proky.booking.dto.UserDto;
import com.proky.booking.exception.ServiceException;
import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.dao.IUserTypeDao;
import com.proky.booking.persistence.entities.User;
import com.proky.booking.persistence.entities.UserType;
import com.proky.booking.util.PasswordEncryptor;
import com.proky.booking.util.constans.enums.UserTypeEnum;
import com.proky.booking.util.properties.MessageProperties;
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
import java.util.Optional;
import java.util.stream.Collectors;


@Log4j2
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private IUserTypeDao userTypeDao;
    private IUserDao userDao;
    private MessageProperties messageProperties;
    private ModelMapper modelMapper;

    public boolean isAdministrator(User authenticatedUser) {
        final UserType userType = authenticatedUser.getUserType();

        final UserType adminUserType = userTypeDao
                .findByType(UserTypeEnum.ADMIN.type)
                .orElseThrow(() -> new ServiceException(messageProperties.notFoundEntity));

        return userType.equals(adminUserType);
    }

    public PageDto findAllRegisteredUsers(PageDto pageDto) {
        log.info("pageDto in: {}", pageDto.toString());

        final UserType userType = userTypeDao.
                findByType(UserTypeEnum.USER.type)
                .orElseThrow(() -> new ServiceException(messageProperties.notFoundEntity));

        final PaginationService paginationService = getProxyPaginationService();
        paginationService.setPageDto(pageDto);
        paginationService.calculatePagination();

        Pageable pageable = PageRequest.of(paginationService.getCurrentPageIndex(), paginationService.getPageSize());

        final Page<User> allUsersPage = userDao.findAllByType(userType, pageable);
        final List<UserDto> allUsersDto = allUsersPage.get().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        paginationService.setAllPagesAmount(allUsersPage.getTotalPages());
        paginationService.calculatePagination();
        pageDto.setPageList(allUsersDto);
        paginationService.updatePageDto();

        log.info("pageDto after update: {}", pageDto.toString());

        return paginationService.getpageDto();
    }

    @Lookup
    public PaginationService getProxyPaginationService() {
        log.info("paginationService called");
        return null;
    }

}
