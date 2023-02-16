import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class TestJunit{
	
	@Test
	public void test() {
		Flux<String> source = Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
				.filter(name -> name.length() == 4).map(String::toUpperCase);

		Flux<String> requetes = Flux.just("close","close");
		
		Mono<Boolean> allClose = requetes.all(s-> s.equals("close"));
		Mono<Boolean> hasElement = requetes.hasElements();
		
		Mono<Boolean> zip = Mono.zip(allClose, hasElement).map(tuple-> tuple.getT1() && tuple.getT2());
		
		StepVerifier
		  .create(zip)
		  .expectNext(true)
		  .expectComplete()
		  .verify();	
	}
	
	@Test
	public void testFilteredWithNameInUppercase() {
		Flux<String> source = Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
				.filter(name -> name.length() == 4).map(String::toUpperCase); //JOHN,MARK,CATE,CLOE


		StepVerifier
		  .create(source)
		  .expectNext("JOHN","MARK","CLOE","CATE")
		  .expectComplete()
		  .verify();	
	}
	
	
	@Test
	public void testInactiveCasAllNotClose() {

		Flux<String> requests = Flux.just("close","open");
		
		Mono<Boolean> noRequestOrAllNotClose = requests.all(s-> !s.equals("close"));
		Boolean active = true;
		StepVerifier
		  .create(noRequestOrAllNotClose)
		  .expectNext(active)
		  .expectComplete()
		  .verify();	
	}
	
	
	@Test
	public void testInactiveCasNotElement() {

		Flux<String> requests = Flux.just();
		
		Mono<Boolean> noRequestOrAllNotClose = requests.all(s-> !s.equals("close"));
		Boolean active = true;
		StepVerifier
		  .create(noRequestOrAllNotClose)
		  .expectNext(active)
		  .expectComplete()
		  .verify();	
	}
	


}
