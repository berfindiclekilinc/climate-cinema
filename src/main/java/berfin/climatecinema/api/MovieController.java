package berfin.climatecinema.api;

import berfin.climatecinema.business.concretes.GenreService;
import berfin.climatecinema.business.concretes.MovieService;
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

    @Autowired
    public MovieController(MovieService movieService, GenreService genreService) {
        this.movieService = movieService;
        this.genreService = genreService;
    }

    @PostMapping("/addRandomMovie")
    public Result addRandomMovie() throws IOException {
        return new SuccessResult();
    }

    @PostMapping("/asdaadd")
    public void genre(String genreType) throws IOException {
        System.out.println(genreService.getGenreId(genreType));
    }
}
