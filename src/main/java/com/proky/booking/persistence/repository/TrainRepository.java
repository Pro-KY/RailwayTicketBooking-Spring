package com.proky.booking.persistence.repository;

import com.proky.booking.persistence.entities.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Long> {
}
