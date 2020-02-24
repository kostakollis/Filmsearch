package filmsearch.search;

import filmsearch.film.Film;
import filmsearch.film.FilmDTO;
import filmsearch.film.FilmMapper;
import filmsearch.film.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kosta on 25.11.2017.
 */
@Component
public class YearSearchService {

    @Autowired
    FilmRepository filmRepository;
    @Autowired
    FilmMapper filmMapper;

    public YearSearchDTO getByYear(int year){
        List<Film> filmList = filmRepository.findByYear(year);
        if(filmList != null) {
            YearSearchDTO yearSearchDTO = new YearSearchDTO();
            yearSearchDTO.setYear(year);
            List<FilmDTO> filmDTOList = new ArrayList<>();
            filmList.forEach(film -> filmDTOList.add(filmMapper.mapToDTO(film)));
            double avgRating = 0;
            for (FilmDTO filmDTO : filmDTOList) {
                avgRating += filmDTO.getImdbRating();
            }
            avgRating = avgRating / filmDTOList.size();
            yearSearchDTO.setFilmDTOs(filmDTOList);
            yearSearchDTO.setAverageRating(avgRating);
            return yearSearchDTO;
        }
        return null;
    }

    public List<YearSearchDTO> getForRange(int from, int to){
        List<YearSearchDTO> yearSearchDTOList = new ArrayList<>();
        for(int i = from; i <= to; i++){
            YearSearchDTO yearSearchDTO = getByYear(i);
            if(yearSearchDTO != null){
                yearSearchDTOList.add(yearSearchDTO);
            }
        }
        return yearSearchDTOList;
    }
}
