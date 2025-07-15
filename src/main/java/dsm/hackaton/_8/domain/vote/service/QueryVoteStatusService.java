package dsm.hackaton._8.domain.vote.service;

import dsm.hackaton._8.domain.vote.domain.Vote;
import dsm.hackaton._8.domain.vote.domain.repository.VoteRepository;
import dsm.hackaton._8.domain.vote.presentation.dto.response.VoteLawResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryVoteStatusService {

    private final VoteRepository voteRepository;

    @Transactional(readOnly = true)
    public VoteLawResponse execute(Long lawId) {
        Vote vote = voteRepository.findVoteByLawId(lawId);

        return VoteLawResponse.builder()
                .agree(vote.getAgree())
                .disagree(vote.getDisagree())
                .totalVote(vote.getTotalVote())
                .build();
    }
}
