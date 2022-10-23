package ru.otus.spring.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.library.domain.result.BookTarget;
import ru.otus.spring.library.domain.result.CommentTarget;
import ru.otus.spring.library.domain.source.Comment;
import ru.otus.spring.library.repository.result.BookTargetRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentTransform {
    private final BookTargetRepository bookTargetRepository;

    public BookTarget convert(Comment comment) {
        BookTarget book = bookTargetRepository.findByLegacyId(comment.getBook().getId()).orElseThrow(RuntimeException::new);
        log.info("нашли книгу " + book.getTitle() + " для комментария " + comment.getText() + " с id книги " + comment.getBook().getId());
        var comments = book.getComments();
        comments.add(new CommentTarget(comment.getText(), comment.getId()));
        book.setComments(comments);
        return book;
    }
}
