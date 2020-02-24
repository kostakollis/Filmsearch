package filmsearch.genre;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Kosta on 25.11.2017.
 */
public interface GenreRepository extends MongoRepository<Genre, String> {
    Genre findByName(String name);
}
