package filmsearch.film;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Kosta on 25.11.2017.
 */
@Component
public interface FilmRepository extends MongoRepository<Film, String>{
    Film findByTitle(String title);
    Film findByImdbID(String imdbId);
    @Query(value = "{ 'actors.name' : ?0}", fields = "{ 'actors.name' : 0 }")
    List<Film> findByActor(String name);
    List<Film> findByYear(int year);
}
