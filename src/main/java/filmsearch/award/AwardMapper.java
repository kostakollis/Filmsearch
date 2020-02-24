package filmsearch.award;

import org.springframework.stereotype.Component;

/**
 * Created by Kosta on 25.11.2017.
 */
@Component
public class AwardMapper {

    public AwardDTO mapToDTO(Award award){
        AwardDTO awardDTO = new AwardDTO();
        awardDTO.setId(award.getId());
        awardDTO.setName(award.getName());
        return awardDTO;
    }
}
