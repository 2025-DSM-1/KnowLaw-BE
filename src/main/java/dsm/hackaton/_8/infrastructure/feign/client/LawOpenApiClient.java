package dsm.hackaton._8.infrastructure.feign.client;

import dsm.hackaton._8.global.config.FeignConfig;
import dsm.hackaton._8.infrastructure.feign.dto.response.LawApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "lawOpenApiClient",
        url = "${openApi.law.url}",
        configuration = FeignConfig.class
)
public interface LawOpenApiClient {

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    LawApiResponse getLaws();
}
