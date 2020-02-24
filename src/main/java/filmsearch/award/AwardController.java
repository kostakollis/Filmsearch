package filmsearch.award;

import com.wordnik.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kosta on 25.11.2017.
 */
@Api(basePath = "/api/award", value = "Awards", description = "Awards endpoint")
@RestController
@RequestMapping(value = "/api/award")
public class AwardController {

    @Autowired
    AwardService awardService;

    @Autowired
    AwardMapper awardMapper;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<AwardDTO> getAllAwards(){
        List<AwardDTO> awardDTOList = new ArrayList<>();
        awardService.getAllAwards().forEach(award -> awardDTOList.add(awardMapper.mapToDTO(award)));
        return awardDTOList;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteAllAwards(){
        awardService.deleteAllAwards();
    }
}
