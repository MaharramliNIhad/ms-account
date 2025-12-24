package org.company.msaccount.configuration;

import org.company.msaccount.service.CardEventService;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

        public static final String EXCHANGE = "account.exchange";
        public static final String ACCOUNT_CREATED = "account.created";
        public static final String ACCOUNT_DEACTIVATED = "account.deactivated";

        @Bean
        public TopicExchange accountExchange() {
            return new TopicExchange(EXCHANGE);
        }
        @Bean
        public Jackson2JsonMessageConverter jsonMessageConverter() {
                Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
                DefaultClassMapper mapper = new DefaultClassMapper();
                Map<String,Class<?>> idMap = new HashMap<String,Class<?>>();
                idMap.put("com.company.ms_card.service.CardEventService", CardEventService.class);
                mapper.setIdClassMapping(idMap);
                mapper.setTrustedPackages("com.company.ms_card.service", "com.company.ms_account.service");
                converter.setClassMapper(mapper);
                return converter;


        }

        @Bean
        public RabbitTemplate rabbitTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
                RabbitTemplate template = new RabbitTemplate(connectionFactory);
                template.setMessageConverter(jsonMessageConverter());
                return template;
        }

}
