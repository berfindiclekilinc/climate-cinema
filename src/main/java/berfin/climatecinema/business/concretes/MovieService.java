package berfin.climatecinema.business.concretes;

import berfin.climatecinema.dataAccess.abstracts.MovieDao;
import berfin.climatecinema.entities.concretes.Movie;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Service
public class MovieService {

    private MovieDao movieDao;

    @Value("${movie.api.movie.base-url}")
    private String baseUrl;

    @Value("${movie.api.key}")
    private String apiKey;

    public MovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public Movie getMovie(String genreId){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(baseUrl + genreId)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey)
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
                    Movie movie = new Movie();

                    boolean watched = true;
                    while (watched) {
                        JsonObject randomMovieObject = results.get(new Random().nextInt(results.size())).getAsJsonObject();

                        movie.setTmdbId((long) randomMovieObject.get("id").getAsInt());
                        movie.setMovieName(randomMovieObject.get("title").getAsString());
                        movie.setMovieRating(String.valueOf(randomMovieObject.get("vote_average").getAsDouble()));
                        movie.setMovieYear(String.valueOf(Integer.parseInt(randomMovieObject.get("release_date").getAsString().substring(0, 4))));
                        movie.setMovieDesc(randomMovieObject.get("overview").getAsString());
                        movie.setMovieGenre(genreId);

                        if (!isExistbyTmdbId(movie)){
                            watched = false;
                            add(movie);}
                    }
                    return movie;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean isExistbyTmdbId(Movie movie){
        return movieDao.existsByTmdbId(movie.getTmdbId());
    }

    public void add(Movie movie) {
        this.movieDao.save(movie);
    }
}
