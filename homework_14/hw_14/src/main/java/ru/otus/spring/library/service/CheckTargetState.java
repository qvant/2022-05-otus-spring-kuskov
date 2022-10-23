package ru.otus.spring.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.library.repository.result.AuthorTargetRepository;
import ru.otus.spring.library.repository.result.BookTargetRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CheckTargetState {
    private final AuthorTargetRepository authorRepository;
    private final BookTargetRepository bookRepository;

    public void checkMigrationPossible() {
        if (authorRepository.findAll().size() > 0 || bookRepository.findAll().size() > 0) {
            throw new RuntimeException("Target database is not empty");
        }
    }
}
