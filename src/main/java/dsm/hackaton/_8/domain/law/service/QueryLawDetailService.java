package dsm.hackaton._8.domain.law.service;

import dsm.hackaton._8.domain.law.domain.Law;
import dsm.hackaton._8.domain.law.domain.repository.LawRepository;
import dsm.hackaton._8.domain.law.exception.LawNotFoundException;
import dsm.hackaton._8.domain.law.presentatoin.dto.response.LawSummaryContentResponse;
import dsm.hackaton._8.domain.law.presentatoin.dto.response.QueryLawDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
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
                .lawSummaryContent(parseLawSummaryContent(law.getLawSummaryContent()))
                .propositionDate(law.getPropositionDate())
                .backgroundInfo(law.getBackgroundInfo())
                .example(law.getExample())
                .build();
    }

    private List<LawSummaryContentResponse> parseLawSummaryContent(String summaryContent) {
        String[] sentences = summaryContent.split("\\.");

        return Arrays.stream(sentences)
                .map(String::trim)
                .filter(sentence -> !sentence.isEmpty())
                .limit(3)
                .map(sentence -> LawSummaryContentResponse.builder()
                        .summaryElement(sentence + ".")
                        .build())
                .collect(Collectors.toList());
    }
}
