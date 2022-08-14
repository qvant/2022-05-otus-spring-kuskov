package ru.otus.spring.exam.test;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.exam.service.IOService;

@SpringBootTest
class TestExamApplication {

    @MockBean
    IOService ioService;

    @Test
    void contextLoads() {
        Mockito.when(ioService.read()).thenReturn("A");
    }

}
