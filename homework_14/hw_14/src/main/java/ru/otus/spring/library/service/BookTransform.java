package ru.otus.spring.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.otus.spring.library.domain.result.BookTarget;
import ru.otus.spring.library.domain.result.CommentTarget;
import ru.otus.spring.library.domain.source.Book;
import ru.otus.spring.library.domain.source.Comment;
import ru.otus.spring.library.repository.source.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookTransform {

    private final AuthorTransform authorTransform;
    private final CommentRepository commentRepository;

    public BookTarget convert(Book book) {
        ObjectId author = authorTransform.findAuthor(book.getAuthor().getId());
        log.info("Нашли автора " + author.toString() + " для книги " + book.getTitle());
        List<Comment> comments = commentRepository.findByBookId(book.getId());
        List<CommentTarget> commentTargetList = new ArrayList<>();
        for (Comment comment : comments
        ) {
            commentTargetList.add(new CommentTarget(comment.getText(), comment.getId()));
        }
        BookTarget bookTarget = new BookTarget(book.getTitle(), author, book.getGenre(), book.getIsbn(), book.getId(), commentTargetList);
        return bookTarget;
    }

}
