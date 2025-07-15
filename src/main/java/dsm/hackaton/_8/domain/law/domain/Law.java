package dsm.hackaton._8.domain.law.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Law {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "law_serial_number", nullable = false)
    private int lawSerialNumber;  // 의안번호 open pai

    @Column(name = "law_title", nullable = false)
    private String lawTitle;  // 의안제목 open api

    //@Column(name = "law_content", nullable = false)
    //private String lawContent;  // python server 한 줄 요약? 보류

    @Column(name = "law_summary_content", nullable = false)
    private String lawSummaryContent;  // 파이썬 서버에서 받아온 내용 저장

    @Column(name = "law_status", nullable = false)
    private String lawStatus;  // 오픈 api

    @Column(name = "proposition_date", nullable = false)
    private LocalDate propositionDate; // open api 발의일 = 제안일자

    // 받기 어려움
    //@Column(name = "promulgation_date", nullable = false)
    //private LocalDate promulgationDate;  // open api 공포일

    // 받기 어려움
    //@Column(name = "enforcement_date", nullable = false)
    //private LocalDate enforcementDate;  // open api  시행일

    @Column(name = "background_info", nullable = false)
    private String backgroundInfo; // python server

    @Column(name = "example", nullable = false)
    private String example;  // python server
}
