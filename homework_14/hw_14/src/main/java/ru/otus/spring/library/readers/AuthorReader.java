package ru.otus.spring.library.readers;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Service;
import ru.otus.spring.library.domain.source.Author;
import ru.otus.spring.library.repository.source.AuthorRepository;

import java.util.Iterator;

@RequiredArgsConstructor
@Service
public class AuthorReader implements ItemReader<Author> {
    private final AuthorRepository authorRepository;
    private Iterator<Author> authorIterator;

    @BeforeStep
    public void before(StepExecution stepExecution) {
        authorIterator = authorRepository.findAll().iterator();
    }

    @Override
    public Author read() {
        if (authorIterator != null && authorIterator.hasNext()) {
            return authorIterator.next();
        } else {
            return null;
        }
    }
}
