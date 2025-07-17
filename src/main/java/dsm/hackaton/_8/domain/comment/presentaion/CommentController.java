package dsm.hackaton._8.domain.comment.presentaion;

import dsm.hackaton._8.domain.comment.presentaion.dto.request.CreateCommentRequest;
import dsm.hackaton._8.domain.comment.presentaion.dto.response.QueryAllCommentsResponse;
import dsm.hackaton._8.domain.comment.service.CreateCommentService;
import dsm.hackaton._8.domain.comment.service.QueryAllCommentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CreateCommentService createCommentService;

    private final QueryAllCommentsService queryAllCommentsService;

    @PostMapping("/{law-id}")
    public void createComment(@PathVariable("law-id") Long lawId, @RequestBody @Valid CreateCommentRequest request) {
        createCommentService.execute(request, lawId);
    }

    @GetMapping("/{law-id}")
    public QueryAllCommentsResponse queryAllComments(@PathVariable("law-id") Long lawId) {
        return queryAllCommentsService.execute(lawId);
    }
}
