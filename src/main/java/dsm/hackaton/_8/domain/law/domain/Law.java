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
    private int lawSerialNumber;

    @Column(name = "law_title", nullable = false)
    private String lawTitle;

    @Column(name = "law_content", nullable = false)
    private String lawContent;

    @Column(name = "law_summary_content", nullable = false)
    private String lawSummaryContent;

    @Column(name = "law_status", nullable = false)
    private String lawStatus;

    @Column(name = "proposition_date", nullable = false)
    private LocalDate propositionDate;

    @Column(name = "promulgation_date", nullable = false)
    private LocalDate promulgationDate;

    @Column(name = "enforcement_date", nullable = false)
    private LocalDate enforcementDate;

    @Column(name = "background_info", nullable = false)
    private String backgroundInfo;

    @Column(name = "example", nullable = false)
    private String example;
}
