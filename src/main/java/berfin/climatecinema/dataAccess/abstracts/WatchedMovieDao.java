package berfin.climatecinema.dataAccess.abstracts;

import berfin.climatecinema.entities.concretes.WatchedMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchedMovieDao extends JpaRepository<WatchedMovie, Integer> {
}