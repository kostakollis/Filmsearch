package filmsearch.person;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Kosta on 25.11.2017.
 */
public interface PersonRepository extends MongoRepository<Person, String>{
    Person findByName(String name);
}
