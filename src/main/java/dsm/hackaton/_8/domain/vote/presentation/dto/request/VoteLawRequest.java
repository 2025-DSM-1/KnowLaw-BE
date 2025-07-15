package dsm.hackaton._8.domain.vote.presentation.dto.request;

import dsm.hackaton._8.domain.vote.domain.type.VoteType;
import lombok.Getter;

@Getter
public class VoteLawRequest {

    private VoteType voteType;
}
