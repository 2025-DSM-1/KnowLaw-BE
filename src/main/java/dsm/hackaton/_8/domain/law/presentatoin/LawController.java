package dsm.hackaton._8.domain.law.presentatoin;

import dsm.hackaton._8.domain.law.presentatoin.dto.response.QueryAllLawsResponse;
import dsm.hackaton._8.domain.law.presentatoin.dto.response.QueryLawDetailResponse;
import dsm.hackaton._8.domain.law.presentatoin.dto.response.QueryLawLogicResponse;
import dsm.hackaton._8.domain.law.service.QueryAllLawsService;
import dsm.hackaton._8.domain.law.service.QueryLawDetailService;
import dsm.hackaton._8.domain.law.service.QueryLawLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/laws")
@RequiredArgsConstructor
public class LawController {

    private final QueryAllLawsService queryAllLawsService;

    private final QueryLawDetailService queryLawDetailService;

    private final QueryLawLogicService queryLawLogicService;

    @GetMapping
    public QueryAllLawsResponse queryAllLaws() {
        return queryAllLawsService.execute();
    }

    @GetMapping("/{law-id}")
    public QueryLawDetailResponse queryLawDetailResponse(@PathVariable("law-id") Long lawId) {
        return queryLawDetailService.execute(lawId);
    }

    @GetMapping("/logic/{law-id}")
    public QueryLawLogicResponse queryLawLogic(@PathVariable("law-id") Long lawId) {
        return queryLawLogicService.execute(lawId);
    }
}
