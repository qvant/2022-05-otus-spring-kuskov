package ru.otus.spring.library.actuators;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.spring.library.repository.BookRepository;

@Component
@RequiredArgsConstructor
public class WeHasBooksHealthIndicator implements HealthIndicator {

    private final BookRepository bookRepository;

    @Override
    public Health health() {
        // Yes, I know that there must be exists query rather than count
        Long bookCount = bookRepository.count();
        if (bookCount > 0) {
            return Health.up().status(Status.UP).withDetail("message", "We have " + bookCount + " books").build();
        }
        return Health.down().status(Status.DOWN).withDetail("message", "Library is empty").build();
    }
}
