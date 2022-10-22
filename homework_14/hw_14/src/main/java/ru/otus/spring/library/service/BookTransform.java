package ru.otus.spring.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.library.domain.result.AuthorTarget;
import ru.otus.spring.library.domain.result.BookTarget;
import ru.otus.spring.library.domain.source.Book;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookTransform {

    private final AuthorTransform authorTransform;

    public BookTarget convert(Book book) {
        AuthorTarget author = authorTransform.findAuthor(book.getAuthor().getId());
        log.info("Нашли автора " + author.getName() + " для книги " + book.getTitle());
        return new BookTarget(book.getTitle(), author, book.getGenre(), book.getIsbn(), book.getId());
    }

}
