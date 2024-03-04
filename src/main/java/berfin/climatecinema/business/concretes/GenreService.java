package berfin.climatecinema.business.concretes;


import berfin.climatecinema.entities.dtos.Genre;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Component
public class GenreService {
    @Value("${movie.api.genre.base-url}")
    private String baseUrl;

    @Value("${movie.api.key}")
    private String apiKey;

    public String getGenreId(String genreName) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(baseUrl)
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
