package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.spring.service.ExamService;

@SpringBootApplication
public class Main {


	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
		ExamService examService = context.getBean(ExamService.class);
		examService.run();
	}

}
