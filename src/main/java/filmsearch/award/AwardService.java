package filmsearch.award;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Kosta on 25.11.2017.
 */
@Component
public class AwardService {

    @Autowired
    AwardRepository awardRepository;

    public List<Award> getAllAwards(){
        return awardRepository.findAll();
    }

    public void deleteAllAwards(){
        awardRepository.deleteAll();
    }
}
