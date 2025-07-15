package dsm.hackaton._8.domain.user.presentation;

import dsm.hackaton._8.domain.user.presentation.dto.response.QueryMyPageResponse;
import dsm.hackaton._8.domain.user.service.QueryMyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final QueryMyPageService queryMyPageService;

    @GetMapping("/my-page")
    public QueryMyPageResponse queryMyPage() {
        return queryMyPageService.execute();
    }
}
