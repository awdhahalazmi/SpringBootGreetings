package com.coded.SecureBankSystem.service.suggestions;

import com.coded.SecureBankSystem.bo.suggestion.CreateSuggestionRequest;
import com.coded.SecureBankSystem.entity.GuestSuggestionEntity;
import jdk.jshell.SourceCodeAnalysis;

import java.util.List;

public interface SuggestionService {

    void getSuggestionRate ();
    List<GuestSuggestionEntity> getCreateStatusSuggestions();
    List<GuestSuggestionEntity> getRemoveStatusSuggestions();
}
