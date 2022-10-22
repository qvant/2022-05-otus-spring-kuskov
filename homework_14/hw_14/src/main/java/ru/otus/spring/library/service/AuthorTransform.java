package ru.otus.spring.library.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.library.domain.result.AuthorTarget;
import ru.otus.spring.library.domain.source.Author;
import ru.otus.spring.library.repository.result.AuthorTargetRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AuthorTransform {
    private final AuthorTargetRepository authorRepository;
    private final Map<Long, AuthorTarget> authorMap;
    private final List<AuthorTarget> authorList;

    public AuthorTransform(AuthorTargetRepository authorTargetRepository) {
        this.authorList = new ArrayList<AuthorTarget>();
        this.authorRepository = authorTargetRepository;
        this.authorMap = new HashMap<Long, AuthorTarget>();
    }

    public AuthorTarget convert(Author author) {
        var mongoAuthor = new AuthorTarget(author.getName(), author.getId());
        authorList.add(mongoAuthor);
        return mongoAuthor;
    }

    public AuthorTarget findAuthor(Long id) {
        return authorMap.get(id);
    }

    public void gatherAuthors() {
        log.info("Начали вычитку сохраненных авторов");
        var authors = authorRepository.findAll();
        log.info("Закончили вычитку сохраненных авторов");
        for (AuthorTarget a : authors
        ) {
            authorMap.put(a.getLegacyId(), a);
        }
        log.info("Закончили заполнение мапы авторов");
    }
}
