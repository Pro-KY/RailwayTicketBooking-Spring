package com.proky.booking.persistence.repository;

import com.proky.booking.persistence.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
