package ru.otus.spring.library.readers;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Service;
import ru.otus.spring.library.domain.source.Comment;
import ru.otus.spring.library.repository.source.CommentRepository;

import java.util.Iterator;

@RequiredArgsConstructor
@Service
public class CommentReader implements ItemReader<Comment> {
    private final CommentRepository commentRepository;
    private Iterator<Comment> commentIterator;

    @BeforeStep
    public void before(StepExecution stepExecution) {
        commentIterator = commentRepository.findAll().iterator();
    }

    @Override
    public Comment read() {
        if (commentIterator != null && commentIterator.hasNext()) {
            return commentIterator.next();
        } else {
            return null;
        }
    }
}
