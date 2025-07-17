package dsm.hackaton._8.domain.comment.service;

import dsm.hackaton._8.domain.comment.domain.Comment;
import dsm.hackaton._8.domain.comment.domain.repository.CommentRepository;
import dsm.hackaton._8.domain.comment.presentaion.dto.response.CommentResponseElement;
import dsm.hackaton._8.domain.comment.presentaion.dto.response.QueryAllCommentsResponse;
import dsm.hackaton._8.domain.law.domain.Law;
import dsm.hackaton._8.domain.law.domain.repository.LawRepository;
import dsm.hackaton._8.domain.law.exception.LawNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryAllCommentsService {

    private final CommentRepository commentRepository;
    private final LawRepository lawRepository;

    @Transactional(readOnly = true)
    public QueryAllCommentsResponse execute(Long lawId) {
        Law law = lawRepository.findById(lawId).orElseThrow(() -> LawNotFoundException.EXCEPTION);
        List<Comment> comments = commentRepository.findAllByLawId(law.getId());

        List<CommentResponseElement> responseElements = comments.stream()
                .map(comment -> CommentResponseElement.builder()
                        .commentId(comment.getId())
                        .commentType(comment.getCommentType())
                        .voteType(comment.getVoteType())
                        .content(comment.getContent())
                        .author(comment.getUser().getName())
                        .build())
                .collect(Collectors.toList());

        return QueryAllCommentsResponse.builder()
                .comments(responseElements)
                .build();
    }
}
