package com.proky.booking.persistence.repository;

import com.proky.booking.persistence.entities.TrainType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainTypeRepository extends JpaRepository<TrainType, Long> {
}
