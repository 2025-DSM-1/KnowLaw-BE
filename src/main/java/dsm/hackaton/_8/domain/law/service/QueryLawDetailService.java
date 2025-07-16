package dsm.hackaton._8.domain.law.service;

import dsm.hackaton._8.domain.law.domain.Law;
import dsm.hackaton._8.domain.law.domain.repository.LawRepository;
import dsm.hackaton._8.domain.law.exception.LawNotFoundException;
import dsm.hackaton._8.domain.law.presentatoin.dto.response.LawSummaryContentResponse;
import dsm.hackaton._8.domain.law.presentatoin.dto.response.QueryLawDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryLawDetailService {

    private final LawRepository lawRepository;

    @Transactional(readOnly = true)
    public QueryLawDetailResponse execute(Long lawId) {
        Law law = lawRepository.findById(lawId).orElseThrow(() -> LawNotFoundException.EXCEPTION);

        return QueryLawDetailResponse.builder()
                .lawId(law.getId())
                .lawTitle(law.getLawTitle())
                .lawStatus(law.getLawStatus())
                .lawSummaryContent(
                        law.getLawSummaryContent().stream()
                                .map(content -> LawSummaryContentResponse.builder()
                                        .summaryElement(content.getSummaryElement())
                                        .build())
                                .collect(Collectors.toList())
                )
                .propositionDate(law.getPropositionDate())
                .backgroundInfo(law.getBackgroundInfo())
                .example(law.getExample())
                .build();
    }
}
