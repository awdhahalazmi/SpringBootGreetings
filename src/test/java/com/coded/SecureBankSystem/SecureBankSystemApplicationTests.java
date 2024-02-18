package com.coded.SecureBankSystem;

import com.coded.SecureBankSystem.bo.suggestion.CreateSuggestionRequest;
import com.coded.SecureBankSystem.entity.GuestSuggestionEntity;
import com.coded.SecureBankSystem.entity.UserEntity;
import com.coded.SecureBankSystem.repository.GuestSuggestionRepository;
import com.coded.SecureBankSystem.repository.UserRepository;
import com.coded.SecureBankSystem.service.suggestions.GuestSuggestionService;
import com.coded.SecureBankSystem.service.suggestions.SuggestionService;
import com.coded.SecureBankSystem.service.user.UserService;
import com.coded.SecureBankSystem.util.enums.SuggestionsStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SecureBankSystemApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	@MockBean
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@Mock
	private GuestSuggestionRepository suggestionRepository;

	@Autowired
	private SuggestionService suggestionService;

	@Test
	public void whenUsersPasswordIsLargerThan8Digits_thenSuccess(){
		//Arrange phase
		UserEntity userEntity = new UserEntity();
		userEntity.setName("Awdhah");
		userEntity.setPassword("12345678");

		UserEntity userEntity2 = new UserEntity();
		userEntity2.setName("Amal");
		userEntity2.setPassword("1248");


		List<UserEntity> mockUsersEntities= Arrays.asList(
				userEntity,userEntity2);
		when(userRepository.findAll()).thenReturn(mockUsersEntities);

		//Act phase
		List<String> userWithStrongPassword= userService.getALlUsersWithStrongPassword();

		//Assert phase
		List<String> expectedUsersWithStrongPassword= Arrays.asList("Awdhah","Amal");
		assertEquals(expectedUsersWithStrongPassword,userWithStrongPassword);

	}

	public void SuggestionService() {
		MockitoAnnotations.openMocks(this);
		suggestionService = new GuestSuggestionService(suggestionRepository);
	}

	@Test
	public void whenGetCreateStatusSuggestions_thenSuccess() {
		// Arrange
		GuestSuggestionEntity suggestion1 = new GuestSuggestionEntity();
		suggestion1.setSuggestionText("add guide app for the bank system");
		suggestion1.setStatus(SuggestionsStatus.CREATE);

		GuestSuggestionEntity suggestion2 = new GuestSuggestionEntity();
		suggestion2.setSuggestionText("any thing2");
		suggestion2.setStatus(SuggestionsStatus.CREATE);

		List<GuestSuggestionEntity> mockSuggestions = Arrays.asList(suggestion1, suggestion2);
		Mockito.when(suggestionRepository.findByStatus(SuggestionsStatus.CREATE)).thenReturn(mockSuggestions);

		// Act
		List<GuestSuggestionEntity> suggestionsWithCreatedStatus = suggestionService.getCreateStatusSuggestions();

		// Assert
		Assertions.assertEquals(2, suggestionsWithCreatedStatus.size());
		// Ensure all suggestions have CREATE status
		Assertions.assertTrue(suggestionsWithCreatedStatus.stream()
				.allMatch(suggestion -> suggestion.getStatus().equals(SuggestionsStatus.CREATE)));
	}
	@Test
	public void whenGetRemoveStatusSuggestions_thenSuccess() {

		GuestSuggestionEntity suggestionEntity = new GuestSuggestionEntity();
		suggestionEntity.setSuggestionText("test 1");
		suggestionEntity.setStatus(SuggestionsStatus.REMOVE);

		GuestSuggestionEntity suggestionEntity1 = new GuestSuggestionEntity();
		suggestionEntity1.setSuggestionText("any thing2");
		suggestionEntity1.setStatus(SuggestionsStatus.REMOVE);

		GuestSuggestionEntity suggestionEntity2 = new GuestSuggestionEntity();
		suggestionEntity2.setSuggestionText("any thing3");
		suggestionEntity2.setStatus(SuggestionsStatus.CREATE);

		List<GuestSuggestionEntity> mockGustSuggestion = Arrays.asList(
				suggestionEntity1, suggestionEntity2, suggestionEntity);
		when(suggestionRepository.findAll()).thenReturn(mockGustSuggestion);

		//Act

		//Assert
		List<GuestSuggestionEntity> suggestionsWithRemoveStatus= suggestionService.getRemoveStatusSuggestions();

		//Assert
		assertEquals(2,suggestionsWithRemoveStatus.size());

	}
	}



