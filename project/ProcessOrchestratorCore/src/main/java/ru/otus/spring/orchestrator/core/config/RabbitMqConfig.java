package ru.otus.spring.orchestrator.core.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    private static final String MAIN_EXCHANGE_NAME = "process_orchestrator-exchange";
    private static final String MAIN_QUEUE_NAME = "process_orchestrator-queue-tasks";
    private static final String RESULT_QUEUE_NAME = "process_orchestrator-queue-task-results";

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(MAIN_EXCHANGE_NAME);
        return rabbitTemplate;
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(MAIN_EXCHANGE_NAME);
    }
    @Bean
    Queue queueTasks(){
        return new Queue(MAIN_QUEUE_NAME, true);
    }
    @Bean
    Queue queueTaskResults(){
        return new Queue(RESULT_QUEUE_NAME, true);
    }
    @Bean
    public Binding bindingTasks(@Qualifier("queueTasks") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("tasks");
    }

    @Bean

    public Binding bindingTaskResults(@Qualifier("queueTaskResults") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("task-results");
    }
}
