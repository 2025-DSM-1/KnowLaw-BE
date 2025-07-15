package dsm.hackaton._8.domain.comment.presentaion.dto.request;

import dsm.hackaton._8.domain.comment.domain.type.CommentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateCommentRequest {

    @NotNull(message = "commentType은 필수입니다.")
    private CommentType commentType;

    @NotBlank(message = "content는 공백일 수 없습니다.")
    private String content;
}
