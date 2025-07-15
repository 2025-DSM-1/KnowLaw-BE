package dsm.hackaton._8.infrastructure.feign.client;

import dsm.hackaton._8.infrastructure.feign.dto.response.LawApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "lawOpenApiClient",
        url = "${openApi.law.url}"
)
public interface LawOpenApiClient {

    @GetMapping
    LawApiResponse getLaws();
}
