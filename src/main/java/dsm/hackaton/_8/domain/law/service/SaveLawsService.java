package dsm.hackaton._8.domain.law.service;

import dsm.hackaton._8.domain.event.LawUpdateEvent;
import dsm.hackaton._8.domain.law.domain.Law;
import dsm.hackaton._8.domain.law.domain.LawSummaryContent;
import dsm.hackaton._8.domain.law.domain.repository.LawRepository;
import dsm.hackaton._8.domain.vote.domain.Vote;
import dsm.hackaton._8.domain.vote.domain.repository.VoteRepository;
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
    private final VoteRepository voteRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void execute() {
        LawApiResponse lawApiResponses = lawOpenApiClient.getLaws();
        List<LawApiResponseElement> laws = lawApiResponses.getBody().getItems().getItemList();

        for (LawApiResponseElement lawApiResponseElement : laws) {
            int lawSerialNumber = lawApiResponseElement.getLawSerialNumber();

            if (lawApiResponseElement.getMainContent() == null) {
                continue;
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            LawSummaryRequest lawSummaryRequest = new LawSummaryRequest(lawApiResponseElement.getMainContent());
            LawSummaryResponse lawSummaryResponse = lawSummaryClient.summarizeLaw(lawSummaryRequest);

            Law existingLaw = lawRepository.findByLawSerialNumber(lawSerialNumber).orElse(null);

            if (existingLaw == null) {
                List<LawSummaryContent> summaryContentList = lawSummaryResponse.getLawSummaryContent().stream()
                        .map(summary -> LawSummaryContent.builder()
                                .summaryElement(summary.getSummaryElement())
                                .build())
                        .toList();

                Law law = Law.builder()
                        .lawSerialNumber(lawSerialNumber)
                        .lawTitle(lawApiResponseElement.getLawTitle())
                        .lawContent(lawSummaryResponse.getLawContent())
                        .lawSummaryContent(summaryContentList)
                        .lawStatus(lawApiResponseElement.getLawStatus())
                        .propositionDate(lawApiResponseElement.getPropositionDate())
                        .promulgationDate(lawApiResponseElement.getPropositionDate())
                        .resolutionResult(lawApiResponseElement.getLawResult())
                        .backgroundInfo(lawSummaryResponse.getBackgroundInfo())
                        .example(lawSummaryResponse.getExample())
                        .agreeLogic(lawSummaryResponse.getAgreeLogic())
                        .disagreeLogic(lawSummaryResponse.getDisagreeLogic())
                        .build();

                // 먼저 law 저장
                Law savedLaw = lawRepository.save(law);

                Vote vote = Vote.builder()
                        .agree(0)
                        .disagree(0)
                        .totalVote(0)
                        .law(savedLaw) // 저장된 law 사용
                        .build();

                voteRepository.save(vote);
            }

//            List<LawSummaryContent> newSummaryContent = lawSummaryResponse.getLawSummaryContent().stream()
//                    .map(summary -> LawSummaryContent.builder()
//                            .summaryElement(summary.getSummaryElement())
//                            .build())
//                    .toList();
//
//            boolean isUpdated = !existingLaw.getLawSummaryContent().equals(lawSummaryResponse.getLawSummaryContent()) ||
//                    !existingLaw.getAgreeLogic().equals(lawSummaryResponse.getAgreeLogic()) ||
//                    !existingLaw.getDisagreeLogic().equals(lawSummaryResponse.getDisagreeLogic());
//
//            if (isUpdated) {
//                existingLaw.updateLaw(
//                        lawApiResponseElement.getLawTitle(),
//                        lawSummaryResponse.getLawContent(),
//                        newSummaryContent,
//                        lawApiResponseElement.getLawStatus(),
//                        lawApiResponseElement.getPropositionDate(),
//                        lawApiResponseElement.getPropositionDate(),
//                        lawApiResponseElement.getLawResult(),
//                        lawSummaryResponse.getBackgroundInfo(),
//                        lawSummaryResponse.getExample(),
//                        lawSummaryResponse.getAgreeLogic(),
//                        lawSummaryResponse.getDisagreeLogic()
//                );
//                applicationEventPublisher.publishEvent(new LawUpdateEvent(existingLaw));
//            }
        }
    }
}
