package filmsearch.film;

import com.wordnik.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kosta on 25.11.2017.
 */
@Api(basePath = "/api/film", value = "Films", description = "Films endpoints")
@RestController
@RequestMapping("api/film")
public class FilmController {

    @Autowired
    FilmService filmService;

    @Autowired
    FilmMapper filmMapper;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<FilmDTO> getAllFilms(){
        List<FilmDTO> filmDTOs = new ArrayList<>();
        filmService.getAllFilms().forEach(film -> filmDTOs.add(filmMapper.mapToDTO(film)));
        return filmDTOs;
    }

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public List<FilmDTO> getResponse() throws Exception{
        List<FilmDTO> filmDTOs = new ArrayList<>();
        filmService.addNewFilms().forEach(film -> filmDTOs.add(filmMapper.mapToDTO(film)));
       return filmDTOs;
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void deleteAll(){
        filmService.deleteAll();
    }

    @RequestMapping(value = "/get/id/{imdbID}", method = RequestMethod.GET)
    public FilmDTO addOrGetByIMDBId(@PathVariable(value = "imdbID") String imdbID)throws Exception{
        return filmMapper.mapToDTO(filmService.addByIMDBId(imdbID));
    }

    @RequestMapping(value = "/get/title/{title}", method = RequestMethod.GET)
    public FilmDTO addOrGetByTitle(@PathVariable(value = "title") String title) throws Exception{
        return filmMapper.mapToDTO(filmService.addByTitle(title));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable(value = "id") String id){
        filmService.deleteById(id);
    }
}
