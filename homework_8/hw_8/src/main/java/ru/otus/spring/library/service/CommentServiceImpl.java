package ru.otus.spring.library.service;

import org.bson.types.ObjectId;
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
    public void showBookComments(ObjectId bookId) {
        var book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            ioService.printWithParameters("Книга [%s] не найдена", bookId.toString());
            return;
        }
        List<Comment> comments = book.get().getComments();
        if (comments == null) {
            ioService.printWithParameters("Нет комментариев для книги [%s] %s:", book.get().getId(), book.get().getTitle());
            return;
        }
        ioService.printWithParameters("Комментарии для книги [%s] %s:", book.get().getId(), book.get().getTitle());
        long index = 0;
        for (Comment comment : comments
        ) {
            ioService.printWithParameters("[%s] %s", index++, comment.getText());
        }
    }

    @Override
    public void addComment(ObjectId bookId, String text) {
        var book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            ioService.print("Книги с id " + bookId + " не существует");
            return;
        }
        List<Comment> comments = book.get().getComments();
        if (comments == null) {
            comments = new ArrayList<Comment>();
        }
        comments.add(new Comment(text));
        book.get().setComments(comments);
        bookRepository.save(book.get());
    }

    @Override
    public void updateComment(ObjectId bookId, int commentId, String text) {
        var book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            ioService.print("Книги с id " + bookId + " не существует");
            return;
        }
        List<Comment> comments = book.get().getComments();
        if (comments == null) {
            return;
        }
        var comment = comments.get(commentId);
        comment.setText(text);
        comments.set(commentId, comment);
        book.get().setComments(comments);
        bookRepository.save(book.get());
    }

    @Override
    public void deleteComment(ObjectId bookId, int commentId) {
        var book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            ioService.print("Книги с id " + bookId + " не существует");
            return;
        }
        List<Comment> comments = book.get().getComments();
        if (comments == null) {
            return;
        }
        comments.remove(commentId);
        book.get().setComments(comments);
        bookRepository.save(book.get());
    }
}
