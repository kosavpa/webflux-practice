package home.owl.handlers;


import home.owl.data.CatService;
import home.owl.models.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
public class CatHandler {
    private CatService service;

    @Autowired
    public CatHandler(CatService service) {
        this.service = service;
    }

    public Mono<ServerResponse> getAllCats(ServerRequest request){

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAllCats(), Cat.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getCatsByName(ServerRequest request){

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findCatsByName(request.pathVariable("name")), Cat.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getCatsByBreed(ServerRequest request){

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findCatsByBreed(request.pathVariable("breed")), Cat.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getCatById(ServerRequest request){

        return service.findCatById(Long.parseLong(request.pathVariable("id")))
                .flatMap(cat -> ServerResponse.ok().body(cat, Cat.class));
    }

    public Mono<ServerResponse> saveCat(ServerRequest request){

        return request.bodyToMono(Cat.class)
                .flatMap(cat -> {
                    service.saveOrUpdateCat(cat);

                    return ServerResponse.status(HttpStatus.CREATED).build();
                });
    }

    public Mono<ServerResponse> updateCat(ServerRequest request){

        return request.bodyToMono(Cat.class)
                .flatMap(cat -> {
                    service.saveOrUpdateCat(cat);

                    return ServerResponse.ok().build();
                });
    }

    public Mono<ServerResponse> deleteCat(ServerRequest request){
        service.deleteCat(Long.parseLong(request.pathVariable("id")));

        return ServerResponse.ok().build();
    }
}