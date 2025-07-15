package dsm.hackaton._8.domain.comment.presentaion.dto.response;

import dsm.hackaton._8.domain.comment.domain.type.CommentType;
import dsm.hackaton._8.domain.vote.domain.type.VoteType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommentResponseElement {

    private Long commentId;

    private CommentType commentType;

    private VoteType voteType;

    private String content;

    private String author;
}
