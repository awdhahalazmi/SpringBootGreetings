package com.coded.SecureBankSystem.controller.suggestion;

import com.coded.SecureBankSystem.bo.suggestion.CreateSuggestionRequest;
import com.coded.SecureBankSystem.entity.GuestSuggestionEntity;
import com.coded.SecureBankSystem.service.suggestions.GuestSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/suggestions")
public class SuggestionController {

    private final GuestSuggestionService suggestionService;

    @Autowired
    public SuggestionController(GuestSuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @PostMapping("/suggest")
    public ResponseEntity<List<GuestSuggestionEntity>> handleSuggestions(@RequestBody CreateSuggestionRequest suggestionRequest) {
        List<GuestSuggestionEntity> suggestionList = suggestionService.printAndProcessSuggestion(suggestionRequest.getSuggestionText());
        return ResponseEntity.ok(suggestionList);
    }
    @PostMapping("/cretae-suggestion")
    public ResponseEntity<String> processSuggestion(CreateSuggestionRequest suggestionTex){
        suggestionService.processSuggestion(suggestionTex);
        return ResponseEntity.ok("bravo you create a suggestion");
    }
}