package dsm.hackaton._8.domain.law.presentatoin;

import dsm.hackaton._8.domain.law.presentatoin.dto.response.QueryAllLawsResponse;
import dsm.hackaton._8.domain.law.service.QueryAllLawsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/laws")
@RequiredArgsConstructor
public class LawController {

    private final QueryAllLawsService queryAllLawsService;

    @GetMapping
    public QueryAllLawsResponse queryAllLaws() {
        return queryAllLawsService.execute();
    }
}
