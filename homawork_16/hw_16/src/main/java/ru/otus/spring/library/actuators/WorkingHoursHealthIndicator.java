package ru.otus.spring.library.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class WorkingHoursHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        LocalTime currentTime = LocalTime.now();
        if (currentTime.getHour() > 18 || currentTime.getHour() < 9) {
            return Health.down().status(Status.DOWN).withDetail("message", "Рабочие часы с 9 до 18").build();
        }
        return Health.up().status(Status.UP).withDetail("message", "Мы работаем").build();
    }
}
