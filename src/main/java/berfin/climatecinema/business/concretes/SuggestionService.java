package berfin.climatecinema.business.concretes;

import berfin.climatecinema.entities.concretes.Movie;
import berfin.climatecinema.entities.concretes.Weather;
import berfin.climatecinema.entities.dtos.LocationCoordinate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SuggestionService {
    private MovieService movieService;
    private GenreService genreService;
    private LocationService locationService;
    private WeatherService weatherService;
    private GenrePickerService genrePickerService;

    public SuggestionService(MovieService movieService, GenreService genreService,
                             LocationService locationService, WeatherService weatherService,
                             GenrePickerService genrePickerService) {
        this.movieService = movieService;
        this.genreService = genreService;
        this.locationService = locationService;
        this.weatherService = weatherService;
        this.genrePickerService = genrePickerService;
    }

    public String getSuggestions(String city) throws IOException {

        LocationCoordinate loc = locationService.getLocationCoordinates(city);

        Weather weather = weatherService.weatherCall(loc);

        String genre = genrePickerService.suggestGenre(weather.getWeatherType());

        String genreId = genreService.getGenreId(genre);

        Movie movie = movieService.getMovie(genreId);

        return ("There is " + weather.getWeatherType().toLowerCase() + " in " + weather.getWeatherLocation()
                + ".\nYou should watch this movie '" + movie.getMovieName() + "'."
                + "\n" + movie.getMovieDesc() + "\nThe rating for the movie is " + movie.getMovieRating()
                + "/10.\nThe genre of the movie is " + genre.toLowerCase() + ".\nThis movie was made in "
                + movie.getMovieYear() + "."
        );
    }
}