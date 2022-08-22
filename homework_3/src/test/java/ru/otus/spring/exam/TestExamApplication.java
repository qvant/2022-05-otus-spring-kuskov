package ru.otus.spring.exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.exam.service.IOService;

@SpringBootTest
class TestExamApplication {

    @MockBean
    private IOService ioService;

    @BeforeEach
    void SetUp(){
        Mockito.when(ioService.read()).thenReturn("A");
    }
    @Test
    void contextLoads() {

    }

}
