package ru.otus.spring.library.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.otus.spring.library.domain.result.AuthorTarget;
import ru.otus.spring.library.domain.source.Author;
import ru.otus.spring.library.repository.result.AuthorTargetRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class AuthorTransform {
    private final Map<Long, ObjectId> authorMap;

    public AuthorTransform() {
        this.authorMap = new ConcurrentHashMap<>();
    }

    public AuthorTarget convert(Author author) {
        ObjectId objectId = new ObjectId();
        var mongoAuthor = new AuthorTarget(objectId.toString(), author.getName(), author.getId());
        authorMap.put(author.getId(), objectId);
        return mongoAuthor;
    }

    public ObjectId findAuthor(Long id) {
        return authorMap.get(id);
    }


}
