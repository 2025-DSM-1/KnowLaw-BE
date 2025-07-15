package dsm.hackaton._8.domain.comment.presentaion.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QueryAllCommentsResponse {

    private List<CommentResponseElement> comments;
}
