package dsm.hackaton._8.infrastructure.feign.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "item")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LawApiResponseElement {

    @JacksonXmlProperty(localName = "billNo")
    private int lawSerialNumber; // 의안번호 (Proposal Number)

    @JacksonXmlProperty(localName = "billName")
    private String lawTitle; // 의안명 (Proposal Name)

    @JacksonXmlProperty(localName = "proposeDt")
    private LocalDate propositionDate; // 제안 일자 (Proposal Date) or 발의일

    @JacksonXmlProperty(localName = "generalResult")
    private String lawResult; // 의결 결과 (Resolution Result)

    @JacksonXmlProperty(localName = "summary")
    private String mainContent; // 주요 내용 (Key Contents)

    @JacksonXmlProperty(localName = "procStageCd")
    private String lawStatus; // 심사 진행 상태 (Review Progress Status)
}
