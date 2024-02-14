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
		//Arrange
		GuestSuggestionEntity suggestionEntity = new GuestSuggestionEntity();
		suggestionEntity.setSuggestionText("any thing1");
		suggestionEntity.setSuggestionsStatus(SuggestionsStatus.CREATE);

		GuestSuggestionEntity suggestionEntity1 = new GuestSuggestionEntity();
		suggestionEntity1.setSuggestionText("any thing2");
		suggestionEntity1.setSuggestionsStatus(SuggestionsStatus.CREATE);

		GuestSuggestionEntity suggestionEntity2 = new GuestSuggestionEntity();
		suggestionEntity2.setSuggestionText("any thing");
		suggestionEntity2.setSuggestionsStatus(SuggestionsStatus.REMOVE);

		List<GuestSuggestionEntity> mockGustSuggestion= Arrays.asList(
				suggestionEntity1,suggestionEntity2,suggestionEntity);
		when(suggestionRepository.findAll()).thenReturn(mockGustSuggestion);

		//Act
		List<GuestSuggestionEntity> suggestionsWithCreatedStatus= suggestionService.getCreateStatusSuggestions();

		//Assert
		List<GuestSuggestionEntity> expectedSuggestionsWithCreatedStatus= Arrays.asList(suggestionEntity1,suggestionEntity);
		assertEquals(expectedSuggestionsWithCreatedStatus,suggestionsWithCreatedStatus);
	}

	@Test
	public void whenGetRemoveStatusSuggestions_thenSuccess() {

//		List<GuestSuggestionEntity> removeSuggestions = List.of(new GuestSuggestionEntity("Remove suggestion 1", SuggestionsStatus.REMOVE),
//				new GuestSuggestionEntity("Remove suggestion 2", SuggestionsStatus.REMOVE));
//		when(suggestionRepository.findAll() ).thenReturn(removeSuggestions);
//
//
//		assertEquals(2, suggestionService.getRemoveStatusSuggestions().size());
	}
}


