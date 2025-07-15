package dsm.hackaton._8.global.scheduler;

import dsm.hackaton._8.domain.law.service.SaveLawsService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LawScheduler {

    private final SaveLawsService saveLawsService;

    @Scheduled(cron = "0 */5 * * * ?")
    public void saveLawsEveryFiveMinutes() {
        saveLawsService.execute();
    }
}
