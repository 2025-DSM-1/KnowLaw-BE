package dsm.hackaton._8.domain.user.service;

import dsm.hackaton._8.domain.user.domain.User;
import dsm.hackaton._8.domain.user.facade.UserFacade;
import dsm.hackaton._8.domain.user.presentation.dto.response.QueryMyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryMyPageService {

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryMyPageResponse execute() {
        User user = userFacade.getCurrentUser();

        return QueryMyPageResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
