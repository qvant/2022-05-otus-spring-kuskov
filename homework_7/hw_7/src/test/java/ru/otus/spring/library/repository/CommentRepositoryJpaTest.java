package ru.otus.spring.library.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.library.domain.Book;
import ru.otus.spring.library.domain.Comment;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JPA repository for comments")
@DataJpaTest
@Import(CommentRepositoryJpa.class)
public class CommentRepositoryJpaTest {

    private static final long EXISTED_BOOK_ID = 1;
    private static final long EXISTED_COMMENT_ID = 1;
    private static final long EXPECTED_COMMENTS_COUNT = 1;
    private static final long EXISTED_COMMENT_BOOK_ID = 1;
    private static final String EXISTED_COMMENT_TEXT = "Классика из школьной программы";
    private static final String NEW_COMMENT_TEXT = "Гы, лол";

    @Autowired
    private CommentRepositoryJpa commentRepositoryJpa;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void checkCommentCreated() {
        Book book = testEntityManager.find(Book.class, EXISTED_BOOK_ID);
        Comment comment = new Comment(book, NEW_COMMENT_TEXT);
        commentRepositoryJpa.save(comment);
        Comment savedComment = testEntityManager.find(Comment.class, comment.getId());
        assertThat(savedComment).usingRecursiveComparison().isEqualTo(comment);
    }

    @Test
    void checkCommentFoundById() {
        Comment comment = commentRepositoryJpa.findById(EXISTED_COMMENT_ID).get();
        assertEquals(comment.getId(), EXISTED_COMMENT_ID);
        assertEquals(comment.getText(), EXISTED_COMMENT_TEXT);
    }

    @Test
    void checkCommentUpdated() {
        Comment comment = testEntityManager.find(Comment.class, EXISTED_COMMENT_ID);
        String newComment = "Test";
        comment.setText(newComment);
        commentRepositoryJpa.save(comment);
        Comment modifiedComment = testEntityManager.find(Comment.class, EXISTED_COMMENT_ID);
        assertThat(comment).usingRecursiveComparison().isEqualTo(modifiedComment);
    }

    @Test
    void checkCommentDeleted() {
        commentRepositoryJpa.deleteById(EXISTED_COMMENT_ID);
        long commentsCount = commentRepositoryJpa.findByBookId(EXISTED_COMMENT_BOOK_ID).size();
        assertEquals(commentsCount, EXPECTED_COMMENTS_COUNT - 1);
        assertNull(testEntityManager.find(Comment.class, EXISTED_COMMENT_ID), "Comment wasn't deleted properly");
    }

    @Test
    void checkGetByBookId() {
        List<Comment> comments = commentRepositoryJpa.findByBookId(EXISTED_COMMENT_BOOK_ID);
        Comment existedComment = testEntityManager.find(Comment.class, EXISTED_COMMENT_ID);
        assertEquals(comments.size(), EXPECTED_COMMENTS_COUNT);
        assertTrue(comments.stream().anyMatch(comment -> existedComment.getText().equals(comment.getText())));
        assertTrue(comments.stream().anyMatch(comment -> existedComment.getId() == comment.getId()));
        assertTrue(comments.stream().anyMatch(comment -> existedComment.getBook().equals(comment.getBook())));

    }

    @Test
    void checkMassCommentCreated() {
        Book book = testEntityManager.find(Book.class, EXISTED_BOOK_ID);
        for (int i = 0; i < 1000; i++) {
            Comment comment = new Comment(book, String.valueOf(i));
            commentRepositoryJpa.save(comment);
        }
    }
}
