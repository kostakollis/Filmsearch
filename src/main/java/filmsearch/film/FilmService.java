package filmsearch.film;

import filmsearch.award.Award;
import filmsearch.award.AwardRepository;
import filmsearch.genre.Genre;
import filmsearch.genre.GenreRepository;
import filmsearch.person.Person;
import filmsearch.person.PersonRepository;
import filmsearch.requests.GetHttpResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kosta on 25.11.2017.
 */
@Component
public class FilmService {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AwardRepository awardRepository;

    @Autowired
    GenreRepository genreRepository;

    public List<Film> getAllFilms(){
        return filmRepository.findAll();
    }

    public void deleteAll(){
        filmRepository.deleteAll();
    }

    public List<Film> addNewFilms(){
        List<Film> addedFilms = new ArrayList<>();
        for(int i = 900000; i < 900010; i++){
            try {
                addedFilms.add(addNewFilm("http://www.omdbapi.com/?i=tt0" + i +"&plot=short&r=json"));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return addedFilms;
    }

    public Film addByIMDBId(String id) throws Exception{
        Film film = filmRepository.findByImdbID(id);
        if(film == null) {
            return addNewFilm("http://www.omdbapi.com/?i=" + id + "&plot=short&r=json");
        }
        return film;
    }

    public Film addByTitle(String title) throws Exception{
        Film film = filmRepository.findByTitle(title.toLowerCase());
        if(film == null) {
            return addNewFilm("http://www.omdbapi.com/?t=" + title.replace(" ", "+").toLowerCase() + "&y=&plot=short&r=json");
        }
        return film;
    }

    public void deleteById(String id){
        filmRepository.delete(id);
    }

    public Film addNewFilm(String url) throws Exception{
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(GetHttpResponse.excuteGet(url));
        Film film = new Film();
        film.setTitle(jsonObject.get("Title").toString().toLowerCase());
        film.setImdbID(jsonObject.get("imdbID").toString());
        film.setCountry(jsonObject.get("Country").toString());

        film.setDirector(addPeople(jsonObject.get("Director").toString()));
        String[] genres = jsonObject.get("Genre").toString().split(",");
        List<Genre> genresToAdd = new ArrayList<>();
        for (int i = 0; i < genres.length; i++) {
            genres[i] = genres[i].replaceAll("[^a-zA-Z]", "");
            Genre genre = genreRepository.findByName(genres[i]);
            if (genre == null) {
                genre = new Genre(genres[i]);
                genreRepository.save(genre);
            }
            genresToAdd.add(genre);
        }
        film.setGenre(genresToAdd);
        if(jsonObject.get("imdbRating").toString().matches(".*[0-9].*")){
            film.setImdbRating(Double.parseDouble(jsonObject.get("imdbRating").toString()));
            film.setImdbVotes(Integer.decode(jsonObject.get("imdbVotes").toString().replace(",","")));
        }
        else{
            film.setImdbRating(0.0D);
            film.setImdbVotes(0);
        }

        if(jsonObject.get("Poster").toString().equals("N/A")){
            film.setPoster(null);
        }
        else {
            film.setPoster(new URL(jsonObject.get("Poster").toString()));
        }

        film.setPlot(jsonObject.get("Plot").toString());
        film.setYear(Integer.decode(jsonObject.get("Year").toString().replaceAll("\\D+","")));
        film.setRuntime(jsonObject.get("Runtime").toString());
        if(jsonObject.get("Runtime").toString() == "NOT RATED"){
            film.setRated(false);
        }
        else{
            film.setRated(true);
        }
        film.setLanguage(jsonObject.get("Language").toString());
        film.setType(FilmType.valueOf(jsonObject.get("Type").toString()));

        film.setActors(addPeople(jsonObject.get("Actors").toString()));
        String[] awards = jsonObject.get("Awards").toString().split(",");
        List<Award> awardsToAdd = new ArrayList<>();
        for (int i = 0; i < awards.length; i++) {
            Award award = awardRepository.findByName(awards[i]);
            if (award == null) {
                award = new Award(awards[i]);
                awardRepository.save(award);
            }
            awardsToAdd.add(award);
        }
        film.setAwards(awardsToAdd);
        film.setReleased(jsonObject.get("Released").toString());
        filmRepository.save(film);
        return film;
    }

    private List<Person> addPeople(String who){
        String[] actors = who.split(",");
        List<Person> actorsToAdd = new ArrayList<>();
        for (int i = 0; i < actors.length; i++) {

            actors[i] = actors[i].replaceAll("[^a-zA-Z\\s]", "").trim();
            Person person = personRepository.findByName(actors[i]);
            if(actors[i].equals("NA") || actors[i].equals("N/A")){
                person = new Person("Unknown");
            }
            if (person == null && !actors[i].equals("N/A")) {
                person = new Person(actors[i]);
                personRepository.save(person);
            }
            actorsToAdd.add(person);
        }
        return actorsToAdd;
    }
}
