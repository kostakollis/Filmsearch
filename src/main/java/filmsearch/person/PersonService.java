package filmsearch.person;

import filmsearch.requests.GetHttpResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

/**
 * Created by Kosta on 25.11.2017.
 */
@Component
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    public void deleteAllPerson(){
        personRepository.deleteAll();
    }

    public List<Person> updateAllPeopleBio(){
        List<Person> people = personRepository.findAll();
        people.forEach(person -> {
            try {
                person = updatePersonBio(person);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
        return people;
    }

    public Person updatePersonByName(String name){

        Person person = personRepository.findByName(name);

        try{

            if(person != null){
                person = updatePersonBio(person);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return person;
    }

    public Person getById(String id){
        return personRepository.findOne(id);
    }

    public Person getByName(String name){
        Person person = new Person(name);
        try {
            person = addNewPerson(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    public Person addNewPerson(String name) throws Exception{
        Person person = personRepository.findByName(name);

        if(person == null) {
            person = new Person(name);
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(GetHttpResponse.excuteGetProxy("http://www.imdb.com/xml/find?json=1&nr=1&nm=on&q=" + person.getName().replace(" ", "+").toLowerCase()));
            JSONArray jsonArray = (JSONArray) object.get("name_popular");
            object = (JSONObject) jsonArray.get(0);
            String id = (String) object.get("id");
            JSONObject jsonObject = (JSONObject) parser.parse(GetHttpResponse.excuteGet("http://imdb.wemakesites.net/api/" + id));
            if (jsonObject.get("status").toString().equals("success")) {
                JSONObject nested = (JSONObject) parser.parse(jsonObject.get("data").toString());
                person.setBio(nested.get("description").toString());
                if (nested.get("image").toString().equals("N/A")) {
                    person.setPhoto(null);
                } else {
                    person.setPhoto(new URL(nested.get("image").toString()));
                }
                person.setOccupation(nested.get("occupation").toString());
                personRepository.save(person);
                return person;
            }
            return null;
        }
        else
        {
            person = updatePersonBio(person);
            personRepository.save(person);
            return person;
        }
    }

    public void deleteById(String id){
        personRepository.delete(id);
    }

    public Person updatePersonBio(Person person) throws Exception{
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(GetHttpResponse.excuteGetProxy("http://www.imdb.com/xml/find?json=1&nr=1&nm=on&q=" + person.getName().replace(" ", "+").toLowerCase()));
        JSONArray jsonArray = (JSONArray) object.get("name_popular");
        object = (JSONObject) jsonArray.get(0);
        String id = (String) object.get("id");
        JSONObject jsonObject = (JSONObject) parser.parse(GetHttpResponse.excuteGet("http://imdb.wemakesites.net/api/" + id));
        if(jsonObject.get("status").toString().equals("success")){
            JSONObject nested = (JSONObject) parser.parse(jsonObject.get("data").toString());
            person.setBio(nested.get("description").toString());
            if (nested.get("image").toString().equals("N/A")) {
                person.setPhoto(null);
            } else {
                person.setPhoto(new URL(nested.get("image").toString()));
            }
            person.setOccupation(nested.get("occupation").toString());
        }
        personRepository.save(person);
        return person;
    }
}
