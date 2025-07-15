package dsm.hackaton._8.domain.comment.presentaion.dto.request;

import dsm.hackaton._8.domain.comment.domain.type.CommentType;
import lombok.Getter;

@Getter
public class CreateCommentRequest {

    private CommentType commentType;

    private String content;
}
