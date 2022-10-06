package ru.otus.spring.library.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.library.domain.Comment;
import ru.otus.spring.library.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentServiceImpl implements CommentService {
    private final BookRepository bookRepository;
    private final IOService ioService;

    public CommentServiceImpl(BookRepository bookRepository, IOService ioService) {
        this.bookRepository = bookRepository;

        this.ioService = ioService;
    }

    @Override
    public void showBookComments(String bookId) {
        bookRepository.findById(bookId).map(book -> {
            List<Comment> comments = book.getComments();
            if (comments == null) {
                ioService.printWithParameters("Нет комментариев для книги [%s] %s:", book.getId(), book.getTitle());
                return null;
            }
            ioService.printWithParameters("Комментарии для книги [%s] %s:", book.getId(), book.getTitle());
            long index = 0;
            for (Comment comment : comments) {
                ioService.printWithParameters("[%s] %s", index++, comment.getText());
            }
            return book;
        }).orElseGet(() -> {
            ioService.printWithParameters("Книга [%s] не найдена", bookId);
            return null;
        });
    }

    @Override
    public void addComment(String bookId, String text) {
        bookRepository.findById(bookId).map(book -> {
            List<Comment> comments = book.getComments();
            if (comments == null) {
                comments = new ArrayList<Comment>();
            }
            comments.add(new Comment(text));
            book.setComments(comments);
            bookRepository.save(book);
            return book;
        }).orElseGet(() -> {
            ioService.print("Книги с id " + bookId + " не существует");
            return null;
        });
    }

    @Override
    public void updateComment(String bookId, int commentId, String text) {
        bookRepository.findById(bookId).map(book -> {
            List<Comment> comments = book.getComments();
            if (comments == null) {
                return null;
            }
            var comment = comments.get(commentId);
            comment.setText(text);
            comments.set(commentId, comment);
            book.setComments(comments);
            bookRepository.save(book);
            return book;
        }).orElseGet(() -> {
            ioService.print("Книги с id " + bookId + " не существует");
            return null;
        });

    }

    @Override
    public void deleteComment(String bookId, int commentId) {
        bookRepository.findById(bookId).map(book -> {
            List<Comment> comments = book.getComments();
            if (comments == null) {
                return null;
            }
            comments.remove(commentId);
            book.setComments(comments);
            bookRepository.save(book);
            return book;
        }).orElseGet(() -> {
            ioService.print("Книги с id " + bookId + " не существует");
            return null;
        });
    }
}
