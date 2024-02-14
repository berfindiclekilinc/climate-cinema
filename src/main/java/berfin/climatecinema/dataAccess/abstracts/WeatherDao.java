package berfin.climatecinema.dataAccess.abstracts;

import berfin.climatecinema.entities.concretes.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDao extends JpaRepository<Weather, Integer> {
}
