import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoVsFluxTest {

	@Test
	public void givenFluxPublisher_whenSubscribed_ThenReturnMultipleValues() {
		Flux<String> stringFlux = Flux.just("Hello", "Baeldung");
		StepVerifier.create(stringFlux);
		// Ajouter le code

	}

	@Test
	public void givenFluxPublisher_WhenSubscribe_ThenReturnMultipleValuesWithError() {
		Flux<String> stringFlux = Flux.just("Hello", "Baeldung", "Error").map(str -> {
			//Ajouter/modifier du code pour que le flux renvoie une erreur apres avoir
			//retourné baeldung
			return str;
		});
		StepVerifier.create(stringFlux);
		// Ajouter le code
	}
	

	
	@Test
	public void givenMono_MonoIsEmpty_ThenReturnDefaultValue() {
		String defaultFirstname = "VALENTINE";
		int maxLinesAuthorized = 1;
				Flux<String> source = Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
				.filter(name -> name.length() == maxLinesAuthorized).map(String::toUpperCase);
				// ajouter du code a la source
				
				StepVerifier
				  .create(source)
				  .expectNext("VALENTINE")
				  .expectComplete()
				  .verify();	
	}
	
	@Test void givenMonoPublisher_WhenErrorReturned_ThenNotTreatmnt() {
		// Ajouter le mono qui produit une erreur (then)
		
		//Creer le test 
	}

	@Test
	public void givenMonoOfList_whenCovertToFlux_ThenReturnFluxValues() {
		List<String> firstnames = new ArrayList<>();
		firstnames.add("chaidou");
		firstnames.add("hubert");
		firstnames.add("momo");

		Mono<List<String>> monoFirstnames = Mono.just(firstnames);
		Flux<String> fluxFirstname = monoTofluxUsingFlatMapIterable(monoFirstnames);
		StepVerifier.create(fluxFirstname).expectNext("chaidou").expectNext("hubert").expectNext("momo")
				.expectComplete().verify();

	}

	private <T> Flux<T> monoTofluxUsingFlatMapIterable(Mono<List<T>> monoList) {
		// ajouter du code
		return null;

	}
	
	

	
}
