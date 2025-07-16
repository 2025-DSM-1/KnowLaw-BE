package dsm.hackaton._8.domain.law.service;

import dsm.hackaton._8.domain.law.domain.Law;
import dsm.hackaton._8.domain.law.domain.repository.LawRepository;
import dsm.hackaton._8.domain.law.exception.LawNotFoundException;
import dsm.hackaton._8.domain.law.presentatoin.dto.response.QueryLawLogicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryLawLogicService {

    private final LawRepository lawRepository;

    @Transactional(readOnly = true)
    public QueryLawLogicResponse execute(Long lawId) {
        Law law = lawRepository.findById(lawId).orElseThrow(() -> LawNotFoundException.EXCEPTION);

        return QueryLawLogicResponse.builder()
                .lawId(lawId)
                .agreeLogic(law.getAgreeLogic())
                .disagreeLogic(law.getDisagreeLogic())
                .build();
    }
}
