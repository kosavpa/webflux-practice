package home.owl.routers;


import home.owl.handlers.CatHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Objects;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class RoutersConfig {
    @Bean
    public RouterFunction<ServerResponse> catRouter(CatHandler handler){

        return RouterFunctions
                .route(
                        GET("/get-cat/{id}")
                                .and(queryParam("id", Objects::nonNull))
                                .and(accept(MediaType.APPLICATION_JSON)),
                        handler::getCatById)
                .andRoute(
                        GET("/get-cat")
                                .and(accept(MediaType.APPLICATION_JSON)),
                        handler::getAllCats)
                .andRoute(
                        GET("/get-cat/{name}")
                                .and(queryParam("name", Objects::nonNull))
                                .and(accept(MediaType.APPLICATION_JSON)),
                        handler::getCatsByName)
                .andRoute(
                        GET("/get-cat/{breed}")
                                .and(queryParam("breed", Objects::nonNull))
                                .and(accept(MediaType.APPLICATION_JSON)),
                        handler::getCatsByBreed)
                .andRoute(
                        DELETE("/delete-cat/{id}")
                                .and(queryParam("id", Objects::nonNull))
                                .and(accept(MediaType.APPLICATION_JSON)),
                        handler::deleteCat)
                .andRoute(
                        POST("/save-cat")
                                .and(accept(MediaType.APPLICATION_JSON)),
                        handler::saveCat)
                .andRoute(
                        POST("/update-cat")
                                .and(accept(MediaType.APPLICATION_JSON)),
                        handler::updateCat);
    }
}
