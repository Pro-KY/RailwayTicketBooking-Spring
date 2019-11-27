package com.proky.booking.persistence.dao.springdata;

import com.proky.booking.persistence.dao.IRouteDao;
import com.proky.booking.persistence.entities.Route;
import com.proky.booking.persistence.repository.RouteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class RouteDaoImpl implements IRouteDao {
    private RouteRepository routeRepository;

    @Override
    public Route save(Route entity) {
       return routeRepository.save(entity);
    }

    @Override
    public Route update(Route entity) {
        return routeRepository.save(entity);
    }

    @Override
    public void delete(Route entity) {
        routeRepository.delete(entity);
    }

    @Override
    public Optional<Route> findById(Long id) {
        return routeRepository.findById(id);
    }
}
