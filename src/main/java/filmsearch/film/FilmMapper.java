package filmsearch.film;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kosta on 25.11.2017.
 */
@Component
public class FilmMapper {

    public FilmDTO mapToDTO(Film film){
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setId(film.getId());
        filmDTO.setTitle(film.getTitle());
        filmDTO.setCountry(film.getCountry());
        filmDTO.setImdbID(film.getImdbID());
        List<String> directors = new ArrayList<>();
        film.getDirector().forEach(director -> directors.add(director.getName()));
        filmDTO.setDirector(directors);
        filmDTO.setLanguage(film.getLanguage());
        filmDTO.setImdbRating(film.getImdbRating());
        filmDTO.setImdbVotes(film.getImdbVotes());
        filmDTO.setPlot(film.getPlot());
        if(film.getPoster() == null){
            filmDTO.setPoster("null");
        }
        else{
            filmDTO.setPoster(film.getPoster().toString());
        }
        filmDTO.setRated(film.isRated());
        filmDTO.setRuntime(film.getRuntime());
        filmDTO.setReleased(film.getReleased());
        filmDTO.setYear(film.getYear());
        filmDTO.setType(film.getType().name());
        List<String> actors = new ArrayList<>();
        film.getActors().forEach(actor -> actors.add(actor.getName()));
        filmDTO.setActors(actors);
        List<String> awards = new ArrayList<>();
        film.getAwards().forEach(award -> awards.add(award.getName()));
        filmDTO.setAwards(awards);
        List<String> genres = new ArrayList<>();
        film.getGenre().forEach(genre -> genres.add(genre.getName()));
        filmDTO.setGenres(genres);

        return filmDTO;
    }

    public FilmDTO mapToDTO(){
        return null;
    }
}
