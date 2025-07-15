package dsm.hackaton._8.domain.vote.domain;

import dsm.hackaton._8.domain.law.domain.Law;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_vote", nullable = false)
    private int totalVote;

    @Column(name = "agree", nullable = false)
    private int agree;

    @Column(name = "disagree", nullable = false)
    private int disagree;

    @OneToOne
    private Law lawId;
}
