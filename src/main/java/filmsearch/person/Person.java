package filmsearch.person;

import org.springframework.data.annotation.Id;

import java.net.URL;

/**
 * Created by Kosta on 25.11.2017.
 */
public class Person {

    @Id
    String id;
    String name;
    String occupation;
    URL photo;
    String bio;

    public Person(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setId(String id) {
        this.id = id;
    }

    public URL getPhoto() {
        return photo;
    }

    public void setPhoto(URL photo) {
        this.photo = photo;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
