package com.proky.booking.service;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.StationDto;
import com.proky.booking.dto.TrainDto;
import com.proky.booking.persistence.entities.Station;
import com.proky.booking.persistence.entities.Train;
import com.proky.booking.persistence.repository.TrainRepository;
import com.proky.booking.util.SqlDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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
    private TrainRepository trainRepository;
    private SqlDateTimeConverter sqlDateTimeConverter;
    private StationService stationService;
    private ModelMapper modelMapper;

    @Transactional
    public PageDto findTrains(final PageDto pageDto, String dateUI, String timeUI, String stationId) {
        log.info("pageDto in: {}", pageDto.toString());

        final Date date = sqlDateTimeConverter.convertToSqlDate(dateUI);
        final Time time = sqlDateTimeConverter.convertToSqlTime(timeUI);
        final Station station = stationService.findById(Long.parseLong(stationId));

        final PaginationService paginationService = getProxyPaginationService();
        paginationService.setPageDto(pageDto);
        paginationService.calculatePagination();

        Pageable pageable = PageRequest.of(pageDto.getCurrentPageIndex(), pageDto.getPageSize());
        paginationService.setAllPagesAmount(pageable.getPageNumber());
        paginationService.calculateEndVisibleIndex();

        final Page<Train> foundTrains = trainRepository.findAllByDepartureDateAndDepartureTimeAndStation(date, time, station, pageable);

        for (Train foundTrain : foundTrains) {
            System.out.println(foundTrain);
        }

        final List<TrainDto> trainDtoList = foundTrains.get().map(this::mapTrainToDto).collect(Collectors.toList());
        pageDto.setPageList(trainDtoList);

        paginationService.updatePageDto();

        for (TrainDto trainDto : trainDtoList) {
            System.out.println(trainDto.toString());
        }

        log.info("pageDto out: {}", pageDto.toString());

        return pageDto;
    }

    @Lookup
    public PaginationService getProxyPaginationService() {
        log.info("paginationService called");
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