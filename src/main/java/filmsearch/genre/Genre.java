package filmsearch.genre;

import org.springframework.data.annotation.Id;

/**
 * Created by Kosta on 25.11.2017.
 */
public class Genre {

    @Id
    String id;
    String name;

    public Genre(String name){
        this.name = name;
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
}
