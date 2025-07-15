package dsm.hackaton._8.domain.vote.presentation;

import dsm.hackaton._8.domain.vote.presentation.dto.request.VoteLawRequest;
import dsm.hackaton._8.domain.vote.presentation.dto.response.VoteLawResponse;
import dsm.hackaton._8.domain.vote.service.QueryVoteStatusService;
import dsm.hackaton._8.domain.vote.service.VoteLawService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteLawService voteLawService;

    private final QueryVoteStatusService queryVoteStatusService;

    @PostMapping("/{law-id}")
    public void voteLaw(@PathVariable("law-id") Long lawId, @RequestBody @Valid VoteLawRequest request) {
        voteLawService.execute(request, lawId);
    }

    @GetMapping("/{law-id}")
    public VoteLawResponse queryVoteStatus(@PathVariable("law-id") Long lawId) {
        return queryVoteStatusService.execute(lawId);
    }
}
