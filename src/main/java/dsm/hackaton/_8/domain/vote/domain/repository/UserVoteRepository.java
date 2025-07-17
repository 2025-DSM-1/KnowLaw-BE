package dsm.hackaton._8.domain.vote.domain.repository;

import dsm.hackaton._8.domain.law.domain.Law;
import dsm.hackaton._8.domain.user.domain.User;
import dsm.hackaton._8.domain.vote.domain.UserVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVoteRepository extends JpaRepository<UserVote, Long> {

    UserVote findByUserId(Long userId);

    Boolean existsByUserAndLaw(User user, Law law);
}
