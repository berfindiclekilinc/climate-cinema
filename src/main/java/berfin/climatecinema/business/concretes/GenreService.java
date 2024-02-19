package berfin.climatecinema.business.concretes;


import berfin.climatecinema.entities.dtos.Genre;
import berfin.climatecinema.utilities.results.Result;
import berfin.climatecinema.utilities.results.SuccessResult;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GenreService {

    public String getGenreId(String genreName) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/genre/movie/list?language=en")
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
            JsonArray genres = jsonObject.getAsJsonArray("genres");

            Genre genre = new Genre();
            for (JsonElement element : genres) {
                JsonObject genreObject = element.getAsJsonObject();
                String name = genreObject.get("name").getAsString();
                if (name.equalsIgnoreCase(genreName)) {
                    genre.setGenreId(genreObject.get("id").getAsString());
                    genre.setGenreName(genreName);
                }
            }

            return genre.getGenreId();
        }

    }
}
