package dsm.hackaton._8.domain.vote.presentation;

import dsm.hackaton._8.domain.vote.presentation.dto.request.VoteLawRequest;
import dsm.hackaton._8.domain.vote.service.VoteLawService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteLawService voteLawService;

    @PostMapping("/{law-id}")
    public void voteLaw(@PathVariable("law-id") Long lawId, VoteLawRequest request) {
        voteLawService.execute(request, lawId);
    }
}
