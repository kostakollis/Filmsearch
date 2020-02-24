package filmsearch.award;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Kosta on 25.11.2017.
 */
public interface AwardRepository extends MongoRepository<Award, String>{
    Award findByName(String name);
}
