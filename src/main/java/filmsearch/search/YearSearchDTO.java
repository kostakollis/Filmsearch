package filmsearch.search;

import filmsearch.film.FilmDTO;

import java.util.List;

/**
 * Created by Kosta on 25.11.2017.
 */
public class YearSearchDTO {
    int year;
    List<FilmDTO> filmDTOs;
    double averageRating;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<FilmDTO> getFilmDTOs() {
        return filmDTOs;
    }

    public void setFilmDTOs(List<FilmDTO> filmDTOs) {
        this.filmDTOs = filmDTOs;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
