package filmsearch.genre;

import org.springframework.stereotype.Component;

/**
 * Created by Kosta on 25.11.2017.
 */
@Component
public class GenreMapper {

    public GenreDTO mapToDTO(Genre genre){
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        return genreDTO;
    }
}
