package dsm.hackaton._8.infrastructure.scheduler;

import dsm.hackaton._8.domain.law.service.SaveLawsService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LawScheduler {

    private final SaveLawsService saveLawsService;

    @Scheduled(fixedDelay = 10 * 60 * 1000, initialDelay = 0, zone = "Asia/Seoul")
    public void saveLawsEveryTenMinutesAfterExecution() {
        saveLawsService.execute();
    }
}
