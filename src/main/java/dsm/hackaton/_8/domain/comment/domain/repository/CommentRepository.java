package dsm.hackaton._8.domain.comment.domain.repository;

import dsm.hackaton._8.domain.comment.domain.Comment;
import dsm.hackaton._8.domain.law.domain.Law;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByLawId(Long LawId);
}
