import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FlatMapVSFlatManyTest {

	
	@Test
	public void givenFluxPublisher_whenFlatMap_ThenReturnMultipleValues() {
		Function<String, String> mapper = String::toUpperCase;
		Flux<String> inFlux = Flux.just("Valentine", ".", "Momo");
		Flux<String> outFlux = inFlux.map(mapper);
		StepVerifier.create(outFlux)
		  .expectNext("VALENTINE", ".", "MOMO")
		  .expectComplete()
		  .verify();
		
	}
	
	@Test
	public void givenFluxPublisher_whenFlatMapMany_ThenReturnMultipleValues() {
		Function<String, String> mapper = String::toUpperCase;
		Function<String, Publisher<String>> otherMapper = s -> Flux.just(s.toUpperCase().split(""));
		Flux<String> inFlux = Flux.just("Valentine", ".", "Momo");
		Flux<String> outFlux = inFlux.flatMap(otherMapper);
		// creer le code
		
	}
	
	
	@Test
	public void givenMonoList_whenFlatManyInvoked_thenReturnMonoList() {
		Mono<List<String>> monoList = monoOfList();
		Flux<String> flux = monoList.flatMapMany(Flux::fromIterable);	
		StepVerifier.create(flux);
        //creer le code 
	}
	
	private Mono<List<String>> monoOfList() {
	    List<String> list = new ArrayList<>();
	    list.add("un");
	    list.add("deux");
	    list.add("trois");
	    return Mono.just(list);
	}
	
	
	private <T> Flux<T> monoTofluxUsingFlatMapMany(Mono<List<T>> monoList) {
	    return null ;
	}
	
	private <T> Mono<List<T>> fluxToMonoUsingFlatMapMany(Flux<T> flux) {
	    return null;

	}
}
