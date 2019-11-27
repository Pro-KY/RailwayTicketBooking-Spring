package com.proky.booking.persistence.dao;
import com.proky.booking.persistence.entities.Station;
import java.util.List;

public interface IStationDao extends IDao<Station> {
    List<Station> findAll();
}
