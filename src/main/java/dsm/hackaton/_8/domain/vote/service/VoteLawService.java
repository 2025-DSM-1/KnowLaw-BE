package dsm.hackaton._8.domain.vote.service;

import dsm.hackaton._8.domain.law.domain.Law;
import dsm.hackaton._8.domain.law.domain.repository.LawRepository;
import dsm.hackaton._8.domain.law.exception.LawNotFoundException;
import dsm.hackaton._8.domain.user.domain.User;
import dsm.hackaton._8.domain.user.facade.UserFacade;
import dsm.hackaton._8.domain.vote.domain.UserVote;
import dsm.hackaton._8.domain.vote.domain.Vote;
import dsm.hackaton._8.domain.vote.domain.exception.LawAlreadyVoteException;
import dsm.hackaton._8.domain.vote.domain.repository.UserVoteRepository;
import dsm.hackaton._8.domain.vote.domain.repository.VoteRepository;
import dsm.hackaton._8.domain.vote.domain.type.VoteType;
import dsm.hackaton._8.domain.vote.presentation.dto.request.VoteLawRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class VoteLawService {

    private final VoteRepository voteRepository;
    private final UserFacade userFacade;
    private final LawRepository lawRepository;
    private final UserVoteRepository userVoteRepository;

    @Transactional
    public void execute(VoteLawRequest request, Long lawId) {
        User user = userFacade.getCurrentUser();
        Vote vote = voteRepository.findVoteByLawId(lawId);
        Law law = lawRepository.findById(lawId).orElseThrow(() -> LawNotFoundException.EXCEPTION);

        boolean alreadyVoted = userVoteRepository.existsByUserAndLaw(user, law);
        if (!alreadyVoted) {
            throw LawAlreadyVoteException.EXCEPTION;
        }

        UserVote userVote = UserVote.builder()
                .user(user)
                .law(law)
                .voteType(request.getVoteType())
                .build();

        if (request.getVoteType() == VoteType.AGREE) {
            vote.incrementAgree();
        } else {
            vote.incrementDisAgree();
        }

        userVoteRepository.save(userVote);
    }
}
