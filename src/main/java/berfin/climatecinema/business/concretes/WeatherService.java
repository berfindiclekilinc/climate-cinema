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
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherService {

    private WeatherDao weatherDao;

    public WeatherService(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    public Weather weatherCall(LocationCoordinate locationCoordinate){
        OkHttpClient client = new OkHttpClient();

        String lat = String.valueOf(locationCoordinate.getLat());
        String lon = String.valueOf(locationCoordinate.getLon());
        String apiKey = "6de995f045ce5790a60794e11a53eeb7";

        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey;

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
