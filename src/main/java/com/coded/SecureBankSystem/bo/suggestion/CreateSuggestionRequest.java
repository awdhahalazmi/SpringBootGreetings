package com.coded.SecureBankSystem.bo.suggestion;

import com.coded.SecureBankSystem.util.enums.SuggestionsStatus;

public class CreateSuggestionRequest {
    private String suggestionText;
    private String rate;
    private SuggestionsStatus status;
    public CreateSuggestionRequest(String suggestionText, String rate, SuggestionsStatus status) {
        this.suggestionText = suggestionText;
        this.rate = rate;
        this.status = status;
    }



    public String getSuggestionText() {
        return suggestionText;
    }

    public void setSuggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public SuggestionsStatus getStatus() {
        return status;
    }

    public void setStatus(SuggestionsStatus status) {
        this.status = status;
    }
}
