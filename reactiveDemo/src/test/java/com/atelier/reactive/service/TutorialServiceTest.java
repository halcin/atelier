package com.atelier.reactive.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.atelier.reactive.model.Tutorial;
import com.atelier.reactive.repository.TutorialRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class TutorialServiceTest {

	@Mock
	private TutorialRepository tutorialRepository;

	@InjectMocks
	private TutorialService tutorialService;

	@Test
	public void givenService_whenFindAllInvoked_thenResultMultipleValues() {
		List<Tutorial> tutorials = new ArrayList<>();
		Tutorial designPattern = new Tutorial("design pattern", "entrainement au design pattern", false, 8);
		Tutorial refactoring = new Tutorial("refactoring", "entrainement a la refactorisation du cde legacy", false, 7);
		tutorials.add(designPattern);
		tutorials.add(refactoring);
		Flux<Tutorial> fluxTutorials = Flux.fromIterable(tutorials);

		when(tutorialRepository.findAll()).thenReturn(fluxTutorials);

		StepVerifier.create(tutorialService.findAll()).expectNext(designPattern, refactoring).expectComplete().verify();

		verify(tutorialRepository, times(1)).findAll();
	}

	//Non testable
	public void givenTitle_whenContainsTitle_thenReturnMutlipleValues() {
		String title = "design";
		List<Tutorial> tutorials = new ArrayList<>();
		Tutorial designPatternExcepted = new Tutorial("design pattern", "entrainement au design pattern", false, 8);
		Tutorial designPattern = new Tutorial("design pattern", "entrainement au design pattern", false, 8);
		tutorials.add(designPattern);
		Flux<Tutorial> fluxTutorials = Flux.fromIterable(tutorials);
		
		when(tutorialRepository.findByTitleContaining(title)).thenReturn(fluxTutorials);

		StepVerifier.create(tutorialService.findByTitleContaining(title)).expectNext(designPatternExcepted).expectComplete().verify();
		verify(tutorialRepository, times(1)).findByTitleContaining(title);

	}

	@Test
	public void givenListofTutoriel_whengetAllTutorial_returnSingleValue() {
		List<Tutorial> tutorials = new ArrayList<>();
		Tutorial designPattern = new Tutorial("design pattern", "entrainement au design pattern", false, 8);
		Tutorial refactoring = new Tutorial("refactoring", "entrainement a la refactorisation du cde legacy", false, 7);
		tutorials.add(designPattern);
		tutorials.add(refactoring);

		Flux<Tutorial> fluxTutorials = Flux.fromIterable(tutorials);

		when(tutorialRepository.findAll()).thenReturn(fluxTutorials);

		StepVerifier.create(tutorialService.getBestTutorials()).expectNext(refactoring).expectComplete().verify();
	}

	@Test
	public void givenEmptyList_whenGetAllTutorial_returnNoSuchException() {
		List<Tutorial> tutorials = new ArrayList<>();

		Flux<Tutorial> fluxTutorials = Flux.fromIterable(tutorials);

		when(tutorialRepository.findAll()).thenReturn(fluxTutorials);

		StepVerifier.create(tutorialService.getBestTutorials()).expectError().verify();
	}

}
