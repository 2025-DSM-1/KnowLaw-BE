package dsm.hackaton._8.domain.vote.domain.repository;

import dsm.hackaton._8.domain.vote.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Vote findVoteByLawId(Long lawId);
}
