import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ExerciceWithSwitchIfEmptyTest {

    @Test
    public void testSwitchIfEmpty() {
        Flux<String> source = Flux.just("Hello, world!");
        Flux<String> defaultFlux = Flux.just("a", "b", "c");
        source.switchIfEmpty(defaultFlux.concatWith(Flux.just("d")));

        StepVerifier.create(defaultFlux)
                .expectNext("a", "b", "c")
                .verifyComplete();
    }

}
