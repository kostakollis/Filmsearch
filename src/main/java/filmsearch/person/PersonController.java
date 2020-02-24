package filmsearch.person;

import com.wordnik.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kosta on 25.11.2017.
 */
@Api(basePath = "/api/person", value = "People", description = "Actors and directors endpoints")
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    PersonMapper personMapper;

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<PersonDTO> getAllPerson(){
        List<PersonDTO> personDTOList = new ArrayList<>();
        personService.getAllPerson().forEach(person -> personDTOList.add(personMapper.mapToDTO(person)));
        return personDTOList;
    }

    @RequestMapping(value = "/updateall", method = RequestMethod.GET)
    public List<PersonDTO> updateAllPeopleBio(){
        List<PersonDTO> personDTOList = new ArrayList<>();
        personService.updateAllPeopleBio().forEach(person -> personDTOList.add(personMapper.mapToDTO(person)));
        return personDTOList;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public PersonDTO createNewPerson(@RequestBody PersonDTO personDTO) throws Exception{
        return personMapper.mapToDTO(personService.addNewPerson(personDTO.getName()));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable(value = "id")String id){
        personService.deleteById(id);
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    public PersonDTO getById(@PathVariable("id") String id){
        return personMapper.mapToDTO(personService.getById(id));
    }

    @RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET)
    public PersonDTO getByName(@PathVariable(value = "name") String name){
        return personMapper.mapToDTO(personService.getByName(name));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void deleteAllPerson(){
        personService.deleteAllPerson();
    }
}
