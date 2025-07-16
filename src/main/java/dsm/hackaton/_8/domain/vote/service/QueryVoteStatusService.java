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

        int agreePercentage = (int) Math.round((double) vote.getAgree() / vote.getTotalVote() * 100);
        int disagreePercentage = (int) Math.round((double) vote.getDisagree() / vote.getTotalVote() * 100);

        return VoteLawResponse.builder()
                .agree(agreePercentage)
                .disagree(disagreePercentage)
                .totalVote(vote.getTotalVote())
                .build();
    }
}
