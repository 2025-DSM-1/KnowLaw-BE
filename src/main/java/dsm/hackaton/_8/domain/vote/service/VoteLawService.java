package dsm.hackaton._8.domain.vote.service;

import dsm.hackaton._8.domain.vote.domain.Vote;
import dsm.hackaton._8.domain.vote.domain.repository.VoteRepository;
import dsm.hackaton._8.domain.vote.presentation.dto.request.VoteLawRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class VoteLawService {

    private final VoteRepository voteRepository;

    @Transactional
    public void execute(VoteLawRequest request, Long lawId) {
        Vote vote = voteRepository.findVoteByLawId(lawId);

        switch (request.getVoteType()) {
            case AGREE:
                vote.incrementAgree();
            default:
                vote.incrementDisAgree();
        }
    }
}
