package berfin.climatecinema.api;

import berfin.climatecinema.business.concretes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RecommendController {

    private SuggestionService suggestionService;

    @Autowired
    public RecommendController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @PostMapping("/getSuggestion")
    public String getSuggestions(String area) throws IOException {
        return this.suggestionService.getSuggestions(area);

    }
}
