package dsm.hackaton._8.domain.law.service;

import dsm.hackaton._8.domain.event.LawUpdateEvent;
import dsm.hackaton._8.domain.law.domain.Law;
import dsm.hackaton._8.domain.law.domain.repository.LawRepository;
import dsm.hackaton._8.infrastructure.feign.client.LawOpenApiClient;
import dsm.hackaton._8.infrastructure.feign.client.LawSummaryClient;
import dsm.hackaton._8.infrastructure.feign.dto.request.LawSummaryRequest;
import dsm.hackaton._8.infrastructure.feign.dto.response.LawApiResponse;
import dsm.hackaton._8.infrastructure.feign.dto.response.LawApiResponseElement;
import dsm.hackaton._8.infrastructure.feign.dto.response.LawSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveLawsService {

    private final LawRepository lawRepository;
    private final LawOpenApiClient lawOpenApiClient;
    private final LawSummaryClient lawSummaryClient;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void execute() {
        LawApiResponse lawApiResponses = lawOpenApiClient.getLaws();
        List<LawApiResponseElement> laws = lawApiResponses.getBody().getItems().getItemList();

        for (LawApiResponseElement lawApiResponseElement : laws) {
            int lawSerialNumber = lawApiResponseElement.getLawSerialNumber();

            LawSummaryRequest lawSummaryRequest = new LawSummaryRequest(lawApiResponseElement.getMainContent());
            LawSummaryResponse lawSummaryResponse = lawSummaryClient.summarizeLaw(lawSummaryRequest);

            Law existingLaw = lawRepository.findByLawSerialNumber(lawSerialNumber).orElse(null);

            if (existingLaw == null) {
                Law law = Law.builder()
                        .lawSerialNumber(lawSerialNumber)
                        .lawTitle(lawApiResponseElement.getLawTitle())
                        .lawSummaryContent(lawSummaryResponse.getLawSummaryContent())
                        .lawStatus(lawApiResponseElement.getLawStatus())
                        .propositionDate(lawApiResponseElement.getPropositionDate())
                        .backgroundInfo(lawSummaryResponse.getBackgroundInfo())
                        .example(lawSummaryResponse.getExample())
                        .agreeLogic(lawSummaryResponse.getAgreeLogic())
                        .disagreeLogic(lawSummaryResponse.getDisagreeLogic())
                        .build();

                lawRepository.save(law);
                continue;
            }

            boolean isUpdated = !existingLaw.getLawSummaryContent().equals(lawSummaryResponse.getLawSummaryContent()) ||
                    !existingLaw.getAgreeLogic().equals(lawSummaryResponse.getAgreeLogic()) ||
                    !existingLaw.getDisagreeLogic().equals(lawSummaryResponse.getDisagreeLogic());

            if (isUpdated) {
                existingLaw.updateLaw(
                        lawApiResponseElement.getLawTitle(),
                        lawSummaryResponse.getLawSummaryContent(),
                        lawApiResponseElement.getLawStatus(),
                        lawApiResponseElement.getPropositionDate(),
                        lawSummaryResponse.getBackgroundInfo(),
                        lawSummaryResponse.getExample(),
                        lawSummaryResponse.getAgreeLogic(),
                        lawSummaryResponse.getDisagreeLogic()
                );
                applicationEventPublisher.publishEvent(new LawUpdateEvent(existingLaw));
            }
        }
    }
}
