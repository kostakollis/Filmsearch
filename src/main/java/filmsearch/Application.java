package filmsearch;

import filmsearch.film.Film;
import filmsearch.film.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by Kosta on 25.11.2017.
 */

@EnableAutoConfiguration
@SpringBootApplication
public class Application {
    @Autowired
    FilmRepository filmRepository;

    public static void main(String[] args){
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }



}
