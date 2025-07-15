package dsm.hackaton._8.domain.law.service;

import dsm.hackaton._8.domain.law.domain.Law;
import dsm.hackaton._8.domain.law.domain.repository.LawRepository;
import dsm.hackaton._8.infrastructure.feign.client.LawOpenApiClient;
import dsm.hackaton._8.infrastructure.feign.client.LawSummaryClient;
import dsm.hackaton._8.infrastructure.feign.dto.request.LawSummaryRequest;
import dsm.hackaton._8.infrastructure.feign.dto.response.LawApiResponse;
import dsm.hackaton._8.infrastructure.feign.dto.response.LawApiResponseElement;
import dsm.hackaton._8.infrastructure.feign.dto.response.LawSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveLawsService {

    private final LawRepository lawRepository;

    private final LawOpenApiClient lawOpenApiClient;

    private final LawSummaryClient lawSummaryClient;

    @Transactional
    public void execute() {
        LawApiResponse lawApiResponses = lawOpenApiClient.getLaws();

        List<LawApiResponseElement> laws = lawApiResponses.getBody().getItems().getItemList();

        for (LawApiResponseElement lawApiResponseElement : laws) {
            LawSummaryRequest lawSummaryRequest = new LawSummaryRequest(lawApiResponseElement.getMainContent());
            LawSummaryResponse lawSummaryResponse = lawSummaryClient.summarizeLaw(lawSummaryRequest);

            Law law = Law.builder()
                    .lawSerialNumber(lawApiResponseElement.getLawSerialNumber())
                    .lawTitle(lawApiResponseElement.getLawTitle())
                    .lawSummaryContent(lawSummaryResponse.getLawSummary())
                    .lawStatus(lawApiResponseElement.getLawStatus())
                    .propositionDate(lawApiResponseElement.getPropositionDate())
                    .backgroundInfo(lawSummaryResponse.getBackgroundInfo())
                    .example(lawSummaryResponse.getExample())
                    .build();

            lawRepository.save(law);
        }
    }
}
