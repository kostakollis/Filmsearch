package filmsearch.person;

import filmsearch.film.Film;
import filmsearch.film.FilmDTO;

import java.util.List;

/**
 * Created by Kosta on 25.11.2017.
 */

public class PersonDTO {
    String id;
    String name;
    String occupation;
    String photo;
    String bio;
    List<FilmDTO> filmDTOs;

    public List<FilmDTO> getFilmDTOs() {
        return filmDTOs;
    }

    public void setFilmDTOs(List<FilmDTO> filmDTOs) {
        this.filmDTOs = filmDTOs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
