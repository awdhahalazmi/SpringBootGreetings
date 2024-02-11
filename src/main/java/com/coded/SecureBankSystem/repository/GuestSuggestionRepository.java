package com.coded.SecureBankSystem.repository;

import com.coded.SecureBankSystem.entity.GuestSuggestionEntity;
import com.coded.SecureBankSystem.util.enums.SuggestionsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestSuggestionRepository extends JpaRepository<GuestSuggestionEntity,Long> {
    List<GuestSuggestionEntity> findByStatus(SuggestionsStatus status);
}
