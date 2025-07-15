package dsm.hackaton._8.domain.comment.domain.repository;

import dsm.hackaton._8.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
