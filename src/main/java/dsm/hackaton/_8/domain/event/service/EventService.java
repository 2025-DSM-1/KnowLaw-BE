package dsm.hackaton._8.domain.event.service;

import dsm.hackaton._8.domain.email.service.EmailService;
import dsm.hackaton._8.domain.event.LawUpdateEvent;
import dsm.hackaton._8.domain.favorite.domain.Favorite;
import dsm.hackaton._8.domain.favorite.domain.repository.FavoriteRepository;
import dsm.hackaton._8.domain.law.domain.Law;
import dsm.hackaton._8.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EventService {

    private final EmailService emailService;
    private final FavoriteRepository favoriteRepository;

    @EventListener
    public void lawUpdated(LawUpdateEvent event) {
        Law law = event.getLaw();

        List<Favorite> favorites = favoriteRepository.findAllByLaw(law);

        for (Favorite favorite : favorites) {
            User user = favorite.getUser();

            emailService.sendEmail(
                    user.getEmail(),
                    "즐겨찾기한 법안이 수정되었습니다.",
                    "\"법안 \\\"\" + law.getLawTitle() + \"\\\" 내용이 업데이트되었습니다. 확인해보세요!\""
            );
        }
    }
}
