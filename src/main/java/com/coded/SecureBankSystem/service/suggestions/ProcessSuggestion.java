package com.coded.SecureBankSystem.service.suggestions;

import com.coded.SecureBankSystem.bo.suggestion.CreateSuggestionRequest;

public interface ProcessSuggestion {
    void processSuggestion(CreateSuggestionRequest suggestionTex);
}
