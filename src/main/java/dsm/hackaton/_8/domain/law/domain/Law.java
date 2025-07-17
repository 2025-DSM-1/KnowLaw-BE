package dsm.hackaton._8.domain.law.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Law {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "law_serial_number", nullable = false, unique = true)
    private int lawSerialNumber;  // 의안번호 open pai

    @Column(name = "law_title", nullable = false)
    private String lawTitle;  // 의안제목 open api

    @Column(name = "law_content", nullable = false)
    private String lawContent;   // python server 한 줄 요약

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "law_id")
    private List<LawSummaryContent> lawSummaryContent;  // 파이썬 서버에서 받아온 내용 저장

    @Column(name = "law_status", nullable = false)
    private String lawStatus;  // 오픈 api

    @Column(name = "proposition_date", nullable = false)
    private LocalDate propositionDate; // open api 발의일 = 제안일자

    @Column(name = "promulgation_date", nullable = false)
    private LocalDate promulgationDate;  // open api 공포일 -> 제안일자

    @Column(name = "resolution_result", nullable = false)
    private String resolutionResult;  // open api  의결결과

    @Column(name = "background_info", nullable = false)
    private String backgroundInfo; // python server

    @Column(name = "example", nullable = false)
    private String example;  // python server

    @Column(name = "agree_logic", nullable = false)
    private String agreeLogic;

    @Column(name = "disagree_logic", nullable = false)
    private String disagreeLogic;

    public void updateLaw(
            String lawTitle,
            String lawContent,
            List<LawSummaryContent> lawSummaryContent,
            String lawStatus,
            LocalDate propositionDate,
            LocalDate promulgationDate,
            String resolutionResult,
            String backgroundInfo,
            String example,
            String agreeLogic,
            String disagreeLogic
    ) {
        this.lawTitle = lawTitle;
        this.lawContent = lawContent;
        this.lawSummaryContent = lawSummaryContent;
        this.lawStatus = lawStatus;
        this.propositionDate = propositionDate;
        this.promulgationDate = promulgationDate;
        this.resolutionResult = resolutionResult;
        this.backgroundInfo = backgroundInfo;
        this.example = example;
        this.agreeLogic = agreeLogic;
        this.disagreeLogic = disagreeLogic;
    }
}
