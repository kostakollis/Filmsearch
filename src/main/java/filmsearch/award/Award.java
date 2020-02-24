package filmsearch.award;

import org.springframework.data.annotation.Id;

/**
 * Created by Kosta on 25.11.2017.
 */
public class Award {
    @Id
    String id;
    String name;

    public Award(String name){
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

    public void setId(String id) {
        this.id = id;
    }
}
