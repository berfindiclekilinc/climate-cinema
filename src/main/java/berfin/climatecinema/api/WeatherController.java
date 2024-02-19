package berfin.climatecinema.api;

import berfin.climatecinema.business.concretes.WeatherService;
import berfin.climatecinema.utilities.results.Result;
import berfin.climatecinema.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping("/getWeather")
    public Result getWeather() {
        return new SuccessResult();
    }
}
