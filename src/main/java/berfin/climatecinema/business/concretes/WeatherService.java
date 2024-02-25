package berfin.climatecinema.business.concretes;

import berfin.climatecinema.dataAccess.abstracts.WeatherDao;
import berfin.climatecinema.entities.concretes.Weather;
import berfin.climatecinema.entities.dtos.LocationCoordinate;
import berfin.climatecinema.utilities.results.Result;
import berfin.climatecinema.utilities.results.SuccessResult;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Component
@Service
public class WeatherService {

    private WeatherDao weatherDao;

    @Value("${weather.api.base-url}")
    private String baseUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherService(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    public Weather weatherCall(LocationCoordinate locationCoordinate){
        OkHttpClient client = new OkHttpClient();

        String lat = String.valueOf(locationCoordinate.getLat());
        String lon = String.valueOf(locationCoordinate.getLon());

        String url = baseUrl + lat + "&lon=" + lon + "&appid=" + apiKey;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.body().string(), JsonObject.class);

            Weather weather = new Weather();
            weather.setWeatherLocation(jsonObject.get("name").getAsString());
            weather.setWeatherType(jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString());

            add(weather);
            return weather;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Result add(Weather weather) {
        this.weatherDao.save(weather);
        return new SuccessResult("Weather added");
    }


    public String getGenreName(){
        return ("fdwfdfdd");
    }
}
