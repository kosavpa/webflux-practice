package home.owl.data;


import home.owl.models.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class CatService {
    private CatRepository repository;

    @Autowired
    public CatService(CatRepository repository) {
        this.repository = repository;
    }

    public Flux<Cat> findAllCats(){
        return repository.findAll();
    }

    public Flux<Cat> findCatsByName(String name){
        return repository.findByCatName(name);
    }

    public Flux<Cat> findCatsByBreed(String breed){
        return repository.findByBreed(breed);
    }

    public Mono<Cat> findCatById(Long id){
        return repository.findById(id);
    }

    public Mono<Cat> saveOrUpdateCat(Cat cat){
        return repository.save(cat);
    }

    public Mono<Void> deleteCat(Long id){
        return repository.deleteById(id);
    }
}