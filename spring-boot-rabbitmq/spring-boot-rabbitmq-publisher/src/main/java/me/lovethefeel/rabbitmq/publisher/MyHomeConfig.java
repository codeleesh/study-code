package me.lovethefeel.rabbitmq.publisher;

import ch.qos.logback.classic.pattern.MessageConverter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

public class MyHomeConfig {

    private static final String EXCHANGE_NAME = "";
    private static final String QUEUE_NAME = "";
    private static final String ROUTING_KEY = "";

    TopicExchange exchange() {

        return new TopicExchange(EXCHANGE_NAME);
    }

    Queue queue() {

        return new Queue(QUEUE_NAME);
    }

    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}