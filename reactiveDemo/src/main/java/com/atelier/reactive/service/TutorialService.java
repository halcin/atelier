package com.atelier.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atelier.reactive.model.Tutorial;
import com.atelier.reactive.repository.TutorialRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TutorialService {

	@Autowired
	TutorialRepository tutorialRepository;

	public Flux<Tutorial> findAll() {
		return tutorialRepository.findAll();
	}

	public Flux<Tutorial> findByTitleContaining(String title) {
		return tutorialRepository.findByTitleContaining(title);
	}

	public Mono<Tutorial> findById(int id) {
		return tutorialRepository.findById(id);
	}

	public Mono<Tutorial> save(Tutorial tutorial) {
		return tutorialRepository.save(tutorial);
	}

	public Mono<Tutorial> update(int id, Tutorial tutorial) {
		return tutorialRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
				.flatMap(optionalTutorial -> {
					if (optionalTutorial.isPresent()) {
						tutorial.setId(id);
						return tutorialRepository.save(tutorial);
					}

					return Mono.empty();
				});
	}

	public Mono<Void> deleteById(int id) {
		return tutorialRepository.deleteById(id);
	}

	public Mono<Void> deleteAll() {
		return tutorialRepository.deleteAll();
	}

	public Flux<Tutorial> findByPublished(boolean isPublished) {
		return tutorialRepository.findByPublished(isPublished);
	}

	public Mono<Tutorial> getBestTutorials() {
		Flux<Tutorial> tutorials = tutorialRepository.findAll();
		return tutorials.collectList().map(list -> {
			return list.stream().min(Comparator.comparing(Tutorial::getRating))
					.orElseThrow(NoSuchElementException::new);
		});
	}

	public Flux<Tutorial> getTutorialsWithRatingMoreThat(int rating) {
		Flux<Tutorial> tutorials = tutorialRepository.findAll();
		return tutorials.filter(tuto -> tuto.getRating() > rating);
	}
}
