package berfin.climatecinema.api;

import berfin.climatecinema.business.concretes.LocationService;
import berfin.climatecinema.business.concretes.WeatherService;
import berfin.climatecinema.entities.dtos.LocationCoordinate;
import berfin.climatecinema.utilities.results.Result;
import berfin.climatecinema.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class WeatherController {

    private WeatherService weatherService;
    private LocationService locationService;

    @Autowired
    public WeatherController(LocationService locationService, WeatherService weatherService) {
        this.weatherService = weatherService;
        this.locationService= locationService;
    }

    @PostMapping("/getWeather")
    public Result getWeather() {
        return new SuccessResult();
    }

    @PostMapping("/getadaser")
    public String weatherrrrr(String city) {
        LocationCoordinate loc = locationService.getLocationCoordinates(city);
        return this.weatherService.weatherCall(loc).toString();
    }
}
