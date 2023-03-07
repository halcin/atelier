import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ExerciceWithSwitchIfEmptyTest {

    @Test
    public void testSwitchIfEmpty() {
        Mono<String> source = Mono.just("Hello, world!");
        Mono<String> defaultSource = Mono.just("Default message");

        Mono<String> result = source.switchIfEmpty(defaultSource);

        StepVerifier.create(result)
                .expectNext("Hello, world!")
                .verifyComplete();
    }

    // à compléter plus tard
    @Test
    public void test() {

    }

}
