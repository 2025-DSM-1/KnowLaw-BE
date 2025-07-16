package dsm.hackaton._8.domain.comment.service;

import dsm.hackaton._8.domain.comment.domain.Comment;
import dsm.hackaton._8.domain.comment.domain.repository.CommentRepository;
import dsm.hackaton._8.domain.comment.presentaion.dto.request.CreateCommentRequest;
import dsm.hackaton._8.domain.law.domain.Law;
import dsm.hackaton._8.domain.law.domain.repository.LawRepository;
import dsm.hackaton._8.domain.law.exception.LawNotFoundException;
import dsm.hackaton._8.domain.user.domain.User;
import dsm.hackaton._8.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateCommentService {

    private final CommentRepository commentRepository;
    private final UserFacade userFacade;
    private final LawRepository lawRepository;

    @Transactional
    public void execute(CreateCommentRequest request, Long lawId) {
        User user = userFacade.getCurrentUser();
        Law law = lawRepository.findById(lawId).orElseThrow(() -> LawNotFoundException.EXCEPTION);

        Comment comment = Comment.builder()
                .commentType(request.getCommentType())
                .content(request.getContent())
                .user(user)
                .law(law)
                .createdAt(LocalDateTime.now())
                .build();

        commentRepository.save(comment);
    }
}
