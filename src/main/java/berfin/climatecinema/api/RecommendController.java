package berfin.climatecinema.api;


import berfin.climatecinema.business.concretes.*;
import berfin.climatecinema.entities.concretes.Movie;
import berfin.climatecinema.entities.concretes.Weather;
import berfin.climatecinema.entities.dtos.LocationCoordinate;
import berfin.climatecinema.utilities.results.Result;
import berfin.climatecinema.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RecommendController {

    private MovieService movieService;
    private GenreService genreService;
    private LocationService locationService;
    private WeatherService weatherService;
    private RecommendService recommendService;

    @Autowired
    public RecommendController(RecommendService recommendService, WeatherService weatherService, MovieService movieService, GenreService genreService, LocationService locationService) {
        this.weatherService = weatherService;
        this.movieService = movieService;
        this.genreService = genreService;
        this.locationService = locationService;
        this.recommendService = recommendService;
    }

    @PostMapping("/getSuggestion")
    public Result getSuggestions(String city) throws IOException {

        LocationCoordinate loc;
        loc = locationService.getLocationCoordinates(city);

        Weather weather;
        weather = weatherService.weatherCall(loc);

        String genre;
        genre = recommendService.suggestedGenre(weather.getWeatherType());

        String genreId;
        genreId = genreService.getGenreId(genre);

        Movie movie;
        movie = movieService.getMovie(genreId);

        return new SuccessResult("The weather is:" + weather.getWeatherType() + " in " + weather.getWeatherLocation() + ".\nYou should watch this movie: " + movie.getMovieName() );
    }
}
