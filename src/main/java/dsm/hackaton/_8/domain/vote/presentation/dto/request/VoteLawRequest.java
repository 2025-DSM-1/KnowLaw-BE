package dsm.hackaton._8.domain.vote.presentation.dto.request;

import dsm.hackaton._8.domain.vote.domain.type.VoteType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class VoteLawRequest {

    @NotNull(message = "voteType은 필수입니다.")
    private VoteType voteType;
}
