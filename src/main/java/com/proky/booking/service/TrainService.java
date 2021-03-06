package com.proky.booking.service;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.StationDto;
import com.proky.booking.dto.TrainDto;
import com.proky.booking.exception.ServiceException;
import com.proky.booking.persistence.dao.ITrainDao;
import com.proky.booking.persistence.entities.Station;
import com.proky.booking.persistence.entities.Train;
import com.proky.booking.util.MessageSourceWrapper;
import com.proky.booking.util.SqlDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class TrainService {
    private ITrainDao trainDao;
    private SqlDateTimeConverter sqlDateTimeConverter;
    private StationService stationService;
    private ModelMapper modelMapper;
    private MessageSourceWrapper messageSourceWrapper;

    @Transactional
    public PageDto findTrains(final PageDto pageDto, String requestDate, String requestTime, String stationId) {
        final Date date = sqlDateTimeConverter.convertToSqlDate(requestDate);
        final Time time = sqlDateTimeConverter.convertToSqlTime(requestTime);
        final Station station = stationService.findById(Long.parseLong(stationId));

        final PaginationService paginationService = getProxyPaginationService();
        paginationService.setPageDto(pageDto);
        paginationService.calculatePageIndex();

        Pageable pageable = PageRequest.of(paginationService.getCurrentPageIndex(), paginationService.getPageSize());

        final Page<Train> foundTrains = trainDao.findAllByDepartureDateAndDepartureTimeAndStation(date, time, station, pageable);
        final List<TrainDto> trainDtoList = foundTrains.get().map(this::mapTrainToDto).collect(Collectors.toList());

        paginationService.setAllPagesAmount(foundTrains.getTotalPages());
        paginationService.changeIndexBoundariesAndButtonsState();
        pageDto.setPageList(trainDtoList);

        paginationService.updatePageDto();

        return paginationService.getpageDto();
    }

    public TrainDto getTrainDtoById(Long id) {
        final Train train = findTrainById(id);
        return modelMapper.map(train, TrainDto.class);
    }

    public Train findTrainById(Long id) {
        return trainDao.findById(id).orElseThrow(() -> new ServiceException(messageSourceWrapper.getMessage("error.notfound")));
    }

    @Lookup
    public PaginationService getProxyPaginationService() {
        return null;
    }

    private TrainDto mapTrainToDto(final Train train) {

        final TrainDto trainDto = modelMapper.map(train, TrainDto.class);
        final List<Station> stations = train.getRoute().getStations();
        final List<StationDto> stationsDto = stations
                .stream()
                .map(routeStation -> modelMapper.map(routeStation, StationDto.class))
                .collect(Collectors.toList());
        trainDto.setStations(stationsDto);
        return trainDto;
    }
}