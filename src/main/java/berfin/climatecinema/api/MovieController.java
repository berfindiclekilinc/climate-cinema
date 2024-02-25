package berfin.climatecinema.api;

import berfin.climatecinema.business.concretes.GenreService;
import berfin.climatecinema.business.concretes.LocationService;
import berfin.climatecinema.business.concretes.MovieService;
import berfin.climatecinema.entities.dtos.LocationCoordinate;
import berfin.climatecinema.utilities.results.Result;
import berfin.climatecinema.utilities.results.SuccessResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;



@RestController
public class MovieController {

    private MovieService movieService;
    private GenreService genreService;
    private LocationService locationService;

    @Autowired
    public MovieController(LocationService locationService, MovieService movieService, GenreService genreService) {
        this.movieService = movieService;
        this.genreService = genreService;
        this.locationService = locationService;
    }


    @PostMapping("/AA")
    public void locationnnn(String city) throws IOException{
        LocationCoordinate loc = locationService.getLocationCoordinates(city);
        System.out.println(loc.toString());
    }
}
