package berfin.climatecinema.business.concretes;


import berfin.climatecinema.entities.dtos.LocationCoordinate;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LocationService {

    public LocationCoordinate getLocationCoordinates(String cityName) {
        OkHttpClient client = new OkHttpClient();

        String city = cityName;
        String apiKey = "6de995f045ce5790a60794e11a53eeb7";

        String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + city + "&limit=5" + "&appid=" + apiKey;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(response.body().string(), JsonArray.class);
            if (jsonArray.size() > 0) {
                JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
                float lat = jsonObject.get("lat").getAsFloat();
                float lon = jsonObject.get("lon").getAsFloat();
                return new LocationCoordinate(lat, lon);
            } else {
                throw new RuntimeException("No location found for the city: " + cityName);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
