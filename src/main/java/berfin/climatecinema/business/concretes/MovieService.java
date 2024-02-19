package berfin.climatecinema.business.concretes;

import berfin.climatecinema.dataAccess.abstracts.MovieDao;
import berfin.climatecinema.entities.concretes.Movie;
import berfin.climatecinema.utilities.results.ErrorResult;
import berfin.climatecinema.utilities.results.Result;
import berfin.climatecinema.utilities.results.SuccessResult;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;

@Service
public class MovieService {

    private MovieDao movieDao;

    public Result getMovie(){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=120&sort_by=popularity.desc")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5ZjY1ZGU4YzZmZjZjZWM2MThhNGY1ZGNjNzYwZGI5MSIsInN1YiI6IjY1Y2IzNTNiY2ZmZWVkMDE4NWMwM2M3OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.M3aepX4bijGYMGfl7dT9kbdiH5rQ8MG_M7_FRGq3Be0")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.body().string(), JsonObject.class);

            if (jsonObject.has("results")) {
                JsonArray results = jsonObject.getAsJsonArray("results");
                if (results.size() > 0) {
                    JsonObject randomMovieObject = results.get(new Random().nextInt(results.size())).getAsJsonObject();

                    Movie movie = new Movie();
                    movie.setTmdbId((long) randomMovieObject.get("id").getAsInt());
                    movie.setMovieName(randomMovieObject.get("title").getAsString());
                    movie.setMovieRating(String.valueOf(randomMovieObject.get("vote_average").getAsDouble()));
                    movie.setMovieYear(String.valueOf(Integer.parseInt(randomMovieObject.get("release_date").getAsString().substring(0, 4))));
                    movie.setMovieDesc(randomMovieObject.get("overview").getAsString());
                    movie.setMovieGenre("Action");

                    add(movie);
                    return new SuccessResult("Movie added to database");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ErrorResult("No results found in the API response.");
    }

    public Result add(Movie movie) {
        this.movieDao.save(movie);
        return new SuccessResult("Movie added");

    }
}
