package ru.otus.spring.library.readers;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Service;
import ru.otus.spring.library.domain.source.Book;
import ru.otus.spring.library.repository.source.BookRepository;

import java.util.Iterator;

@RequiredArgsConstructor
@Service
public class BookReader implements ItemReader<Book> {
    private final BookRepository bookRepository;
    private Iterator<Book> bookIterator;

    @BeforeStep
    public void before(StepExecution stepExecution) {
        bookIterator = bookRepository.findAll().iterator();
    }

    @Override
    public Book read() {
        if (bookIterator != null && bookIterator.hasNext()) {
            return bookIterator.next();
        } else {
            return null;
        }
    }
}
