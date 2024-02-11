package com.coded.SecureBankSystem.service.suggestions;

import com.coded.SecureBankSystem.entity.GuestSuggestionEntity;
import com.coded.SecureBankSystem.repository.GuestSuggestionRepository;
import com.coded.SecureBankSystem.util.enums.SuggestionsStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestSuggestionService {

    private GuestSuggestionRepository suggestionRepository ;

    @Autowired
    public GuestSuggestionService(GuestSuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }

    SuggestionProcessor processSuggestion = suggestionText -> {

        GuestSuggestionEntity suggestionEntity = new GuestSuggestionEntity();
        suggestionEntity.setSuggestionText(suggestionText);

        suggestionRepository.save(suggestionEntity);
    };
    public List<GuestSuggestionEntity> findAllDataSuggestions() {
        List<GuestSuggestionEntity> allSuggestions = suggestionRepository.findAll();
        return allSuggestions.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public List<GuestSuggestionEntity> printAndProcessSuggestion(String suggestionText) {
       return suggestionRepository.findAll();

    }
    public List<GuestSuggestionEntity> findAllCreatedSuggestions(){
        return suggestionRepository.findByStatus(SuggestionsStatus.CREATE);
    }
    public List<GuestSuggestionEntity> findAllRemovedSuggestions(){
        return suggestionRepository.findByStatus(SuggestionsStatus.REMOVE);
    }
}