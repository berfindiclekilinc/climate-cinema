package berfin.climatecinema.dataAccess.abstracts;

import berfin.climatecinema.entities.concretes.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDao extends JpaRepository<Movie, Integer>{

    boolean existsByTmdbId(Long tmdbId);

}
