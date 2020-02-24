package filmsearch.person;

import filmsearch.film.Film;
import filmsearch.film.FilmDTO;
import filmsearch.film.FilmMapper;
import filmsearch.film.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Kosta on 25.11.2017.
 */
@Component
public class PersonMapper {

    @Autowired
    FilmRepository filmRepository;
    @Autowired
    FilmMapper filmMapper;

    Comparator<Film> filmComparator = new Comparator<Film>() {
        @Override
        public int compare(Film o1, Film o2) {
            return o1.getYear() - o2.getYear();
        }
    };

    public PersonDTO mapToDTO(Person person){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setName(person.getName());
        personDTO.setBio(person.getBio());
        if(person.getPhoto() == null){
            personDTO.setPhoto("null");
        }
        else {
            personDTO.setPhoto(person.getPhoto().toExternalForm());
        }
        List<Film> filmList = filmRepository.findByActor(person.getName());

        filmList.sort(filmComparator);
        List<FilmDTO> filmDTOList = new ArrayList<>();
        filmList.forEach(film -> filmDTOList.add(filmMapper.mapToDTO(film)));
        personDTO.setFilmDTOs(filmDTOList);
        personDTO.setOccupation(person.getOccupation());
        return personDTO;
    }
}
