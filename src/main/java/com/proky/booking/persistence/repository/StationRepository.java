package com.proky.booking.persistence.repository;

import com.proky.booking.persistence.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {

}
