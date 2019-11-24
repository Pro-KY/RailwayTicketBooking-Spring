package com.proky.booking.service;

import com.proky.booking.dto.PageDto;
import com.proky.booking.persistence.entities.Station;
import com.proky.booking.persistence.repository.StationRepository;
import com.proky.booking.persistence.repository.TrainRepository;
import com.proky.booking.util.SqlDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class TrainService {
    private TrainRepository trainRepository;
    SqlDateTimeConverter sqlDateTimeConverter;

    public PageDto findTrains(final PageDto pageDto, String dateUI, String timeUI, String stationId) {
        final Date date = sqlDateTimeConverter.convertToSqlDate(dateUI);
        final Time time = sqlDateTimeConverter.convertToSqlTime(timeUI);
        final Station station = new Station(Long.parseLong(stationId));
        final PaginationService paginationService = paginationService();
        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);

        return null;
    }

    @Lookup
    public PaginationService paginationService() {
        log.info("paginationService called");
        return null;
    }
}