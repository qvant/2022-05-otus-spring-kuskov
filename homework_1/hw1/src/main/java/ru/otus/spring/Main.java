package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.QuestionConverter;
import ru.otus.spring.domain.Question;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService service = context.getBean(QuestionService.class);
        Question question = service.read();
        var questionConverter = new QuestionConverter();
        while (question != null){
            System.out.println(questionConverter.convertQuestionToString(question));
            question = service.read();
            if (question == null) {
                break;
            }
        }
        context.close();
    }
}
