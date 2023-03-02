import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class ExerciceWithFluxTest{
	
	@Test
	public void givenFluxPublisher_whenFiltered_ThenReturnMultipleValues() {
		Flux<String> source = Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
				.filter(name -> name.length() == 4).map(String::toUpperCase);

		StepVerifier
		  .create(source)
		  .expectNext("JOHN","MARK","CLOE","CATE")
		  .expectComplete()
		  .verify();	
	}
}
