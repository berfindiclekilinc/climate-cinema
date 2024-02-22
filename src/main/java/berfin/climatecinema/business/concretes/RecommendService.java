package berfin.climatecinema.business.concretes;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class RecommendService {

    private List<String> sunnyAndWarm = Arrays.asList("clear");
    private List<String> rainyAndCloudy = Arrays.asList("rain", "drizzle", "clouds", "tornado", "thunderstorm");
    private List<String> snowyAndCold = Arrays.asList("snow");
    private List<String> warmAndHumid = Arrays.asList("fog", "mist", "smoke", "haze", "dust", "sand", "ash", "squall");

    private List<String> sunnyGenres = Arrays.asList("Action", "Adventure", "Comedy", "Documentary");
    private List<String> rainyGenres = Arrays.asList("Drama", "Romance", "Thriller", "Mystery", "Horror");
    private List<String> snowyGenres = Arrays.asList("Family", "Fantasy", "History", "War", "Western", "Crime");
    private List<String> warmGenres = Arrays.asList("Science Fiction", "Music", "Animation");


    public String suggestedGenre(String weatherTypeInput){

        String weatherType = weatherTypeInput.toLowerCase();
        Random rand = new Random();

        if (sunnyAndWarm.contains(weatherType)) {
            int index = rand.nextInt(sunnyGenres.size());
            return sunnyGenres.get(index);
        } else if (rainyAndCloudy.contains(weatherType)) {
            int index = rand.nextInt(rainyGenres.size());
            return rainyGenres.get(index);
        } else if (snowyAndCold.contains(weatherType)) {
            int index = rand.nextInt(snowyGenres.size());
            return snowyGenres.get(index);
        } else if (warmAndHumid.contains(weatherType)) {
            int index = rand.nextInt(warmGenres.size());
            return warmGenres.get(index);
        } else {
            return "Genre not found for weather type";
        }
    }

}


