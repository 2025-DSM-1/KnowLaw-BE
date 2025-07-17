package dsm.hackaton._8.infrastructure.feign.client;

import dsm.hackaton._8.infrastructure.feign.dto.request.LawSummaryRequest;
import dsm.hackaton._8.infrastructure.feign.dto.response.LawSummaryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "lawSummaryClient", url = "${python.api.url}")
public interface LawSummaryClient {

    @PostMapping(value ="/law/summary")
    LawSummaryResponse summarizeLaw(@RequestBody LawSummaryRequest request);
}
