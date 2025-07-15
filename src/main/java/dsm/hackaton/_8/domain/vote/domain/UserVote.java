package dsm.hackaton._8.domain.vote.domain;

import dsm.hackaton._8.domain.law.domain.Law;
import dsm.hackaton._8.domain.user.domain.User;
import dsm.hackaton._8.domain.vote.domain.type.VoteType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User userId;

    @ManyToOne
    private Law lawId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VoteType voteType; // AGREE, DISAGREE
}

