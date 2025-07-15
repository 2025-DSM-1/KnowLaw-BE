package dsm.hackaton._8.domain.law.presentatoin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryAllLawsResponse {

    private List<LawResponse> laws;
}
