package dsm.hackaton._8.domain.comment.service;

import dsm.hackaton._8.domain.comment.domain.Comment;
import dsm.hackaton._8.domain.comment.domain.repository.CommentRepository;
import dsm.hackaton._8.domain.comment.presentaion.dto.response.CommentResponseElement;
import dsm.hackaton._8.domain.comment.presentaion.dto.response.QueryAllCommentsResponse;
import dsm.hackaton._8.domain.user.domain.User;
import dsm.hackaton._8.domain.user.facade.UserFacade;
import dsm.hackaton._8.domain.vote.domain.UserVote;
import dsm.hackaton._8.domain.vote.domain.repository.UserVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryAllCommentsService {

    private final CommentRepository commentRepository;

    private final UserVoteRepository userVoteRepository;

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryAllCommentsResponse execute() {
        List<Comment> comments = commentRepository.findAll();
        User user = userFacade.getCurrentUser();
        UserVote userVote = userVoteRepository.findUserVoteByUser(user);

        List<CommentResponseElement> responseElements = comments.stream()
                .map(comment -> CommentResponseElement.builder()
                        .commentId(comment.getId())
                        .commentType(comment.getCommentType())
                        .voteType(userVote.getVoteType())
                        .content(comment.getContent())
                        .author(comment.getUser().getName())
                        .build())
                .collect(Collectors.toList());

        return QueryAllCommentsResponse.builder()
                .comments(responseElements)
                .build();
    }
}
