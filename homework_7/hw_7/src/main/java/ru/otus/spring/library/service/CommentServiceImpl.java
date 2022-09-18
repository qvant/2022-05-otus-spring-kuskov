package ru.otus.spring.library.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import ru.otus.spring.library.domain.Comment;
import ru.otus.spring.library.repository.BookRepository;
import ru.otus.spring.library.repository.CommentRepository;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class CommentServiceImpl implements CommentService {
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final IOService ioService;

    public CommentServiceImpl(BookRepository bookRepository, CommentRepository commentRepository, IOService ioService) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.ioService = ioService;
    }

    @Override
    public void showBookComments(Long bookId) {
        List<Comment> comments = commentRepository.findByBookId(bookId);
        for (Comment comment : comments
        ) {
            ioService.printWithParameters("[%d] %s (Книга: [%d] %s)", comment.getId(), comment.getText(), comment.getBook().getId(), comment.getBook().getTitle());
        }
    }

    @Override
    @Transactional
    public void addComment(Long bookId, String text) {
        var book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            ioService.print("Книги с id " + bookId + " не существует");
            return;
        }
        Comment comment = new Comment(book.get(), text);
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void updateComment(long id, String text) {
        var comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            ioService.print("Комментария с id " + id + " не существует");
            return;
        }
        comment.get().setText(text);
        commentRepository.save(comment.get());
    }

    @Override
    @Transactional
    public void deleteComment(long id) {
        try {
            commentRepository.deleteById(id);
        } catch (
                EmptyResultDataAccessException exception) {
            ioService.print("Не существует комментарий с id " + id);
        }
    }
}
