package dsm.hackaton._8.infrastructure.feign.dto.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@JacksonXmlRootElement(localName = "response")
public class LawApiResponse {

    @JacksonXmlProperty(localName = "body")
    private Body body;

    @Getter
    @NoArgsConstructor
    public static class Body {
        @JacksonXmlProperty(localName = "items")
        private Items items;
    }

    @Getter
    @NoArgsConstructor
    public static class Items {
        @JacksonXmlProperty(localName = "item")
        private List<LawApiResponseElement> itemList;
    }
}

