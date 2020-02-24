package filmsearch.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Kosta on 25.11.2017.
 */
@Component
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }

    public void deleteAllGenres(){
        genreRepository.deleteAll();
    }
}
