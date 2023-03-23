import java.util.function.Function;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class CombiningPublishersTest {

	
	private static Integer min = 1;
	private static Integer max = 5;
	Function<String, String> mapper = String::toUpperCase;
	Flux<String> inFlux = Flux.just("baeldung", ".", "com");
	Flux<String> outFlux = inFlux.map(mapper);
	private static Flux<Integer> evenNumbers = Flux.range(min, max).filter(x -> x % 2 == 0);
	private static Flux<Integer> oddNumbers = Flux.range(min, max).filter(x -> x % 2 > 0);

	@Test
	public void givenFluxes_whenZipIsInvoked_thenZip() {
		Flux<Integer> fluxOfIntegers = Flux.zip(evenNumbers, oddNumbers, (a, b) -> a + b);
		// coompletr le code StepVerifier.create(fluxOfIntegers)..
	}

	@Test
	public void givenFluxesWithEmptyCase_whenZipIsInvoked_then() {
		Flux<Integer> emptyFlux = Flux.just(1, 3, 5).filter(x -> x % 2 == 0);
		Flux<Integer> fluxOfIntegers = Flux.zip(emptyFlux, oddNumbers, (a, b) -> a + b);
		// completer le test 
	}

	@Test
	public void givenFluxesWithErrorCase_whenZipIsInvoked_then() {
		Flux<Integer> evenNumbersWithError = Flux.just(2, 100).map(val -> {
			if (val > 15)
				throw new RuntimeException("Throwing Error");
			return Integer.valueOf(val);
		});
		Flux<Integer> fluxOfIntegers = Flux.zip(evenNumbersWithError, oddNumbers, (a, b) -> a + b);
		// corriger le test suivant
		StepVerifier.create(fluxOfIntegers).expectComplete().verify();
	}

	@Test
	public void givenFluxesWithNullCase_whenZipIsInvoked_then() {
		Flux<Integer> evenNumbersWithNullValue = Flux.just(2, null);
		Flux<Integer> fluxOfIntegers = Flux.zip(evenNumbersWithNullValue, oddNumbers, (a, b) -> a);
		//Ajouter le test suivant
	}
	
	@Test
	public void givenFluxesWithEmptyCase_whenZipIsInvokedWithDefault_then() {
		Flux<Integer> fluxWithDefautValue = Flux.just(1, 3, 5).filter(x -> x % 2 == 0).defaultIfEmpty(2);
		Flux<Integer> fluxOfIntegers = Flux.zip(fluxWithDefautValue, oddNumbers, (a, b) -> a + b);
        // ajouter le test 
	}
	
	@Test
	public void givenFluxesWithEmptyCase_whenZipIsInvokedWithDefault_then2() {
		Flux<Integer> fluxWithDefautValue = Flux.just(1, 3, 5).filter(x -> x % 2 == 0);
		Flux<Integer> fluxOfIntegers = Flux.zip(fluxWithDefautValue.defaultIfEmpty(2), oddNumbers, (a, b) -> a + b);
		StepVerifier.create(fluxOfIntegers);
        //ajoute
	}
	
}
