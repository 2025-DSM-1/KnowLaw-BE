package dsm.hackaton._8.domain.law.service;

import dsm.hackaton._8.domain.law.domain.repository.LawRepository;
import dsm.hackaton._8.domain.law.presentatoin.dto.response.LawResponse;
import dsm.hackaton._8.domain.law.presentatoin.dto.response.QueryAllLawsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryAllLawsService {

    private final LawRepository lawRepository;

    @Transactional(readOnly = true)
    public QueryAllLawsResponse execute() {
        List<LawResponse> laws = lawRepository.findAll()
                .stream()
                .map(law -> LawResponse.builder()
                        .lawId(law.getId())
                        .lawSerialNumber(law.getLawSerialNumber())
                        .lawTitle(law.getLawTitle())
                        .lawStatus(law.getLawStatus())
                        .lawContent(law.getLawContent())
                        .promulgationDate(law.getPromulgationDate())
                        .resolutionResult(law.getResolutionResult())
                        .build())
                .collect(Collectors.toList());

        return QueryAllLawsResponse.builder()
                .laws(laws)
                .build();
    }

}
