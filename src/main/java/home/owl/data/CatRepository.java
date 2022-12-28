package home.owl.data;


import home.owl.models.Cat;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;


@Component
public interface CatRepository extends ReactiveCrudRepository<Cat, Long> {
    Flux<Cat> findByBreed(String breed);
    Flux<Cat> findByCatName(String name);
}
