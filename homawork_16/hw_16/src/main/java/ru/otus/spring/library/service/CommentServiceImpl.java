package ru.otus.spring.library.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import ru.otus.spring.library.domain.Book;
import ru.otus.spring.library.domain.Comment;
import ru.otus.spring.library.exceptions.IncorrectReferenceException;
import ru.otus.spring.library.repository.BookRepository;
import ru.otus.spring.library.repository.CommentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class CommentServiceImpl implements CommentService {
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(BookRepository bookRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    @Transactional
    public void addComment(Long bookId, String text) {
        Book book = bookRepository.findById(bookId).orElseThrow(IncorrectReferenceException::new);
        Comment comment = new Comment(book, text);
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void updateComment(long id, String text) {
        Comment comment = commentRepository.findById(id).orElseThrow(IncorrectReferenceException::new);
        comment.setText(text);
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteComment(long id) {
        try {
            commentRepository.deleteById(id);
        } catch (
                EmptyResultDataAccessException exception) {
            throw new IncorrectReferenceException("Не существует комментарий с id " + id);
        }
    }

    @Override
    public List<Comment> findByBookId(Long id) {
        return commentRepository.findByBookId(id);
    }

    @Override
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }
}
