package org.company.msaccount.service;

import org.company.msaccount.configuration.RabbitConfig;
import org.company.msaccount.model.CardCreatedEvent;
import org.company.msaccount.model.CardDeactivatedEvent;
import org.company.msaccount.model.UserRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class CardEventService {
    private final RabbitTemplate rabbitTemplate;

    public CardEventService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void CreateCardCreatedEvent(UserRequest userRequest ) {
        CardCreatedEvent cardCreatedEvent = new CardCreatedEvent();
        cardCreatedEvent.setCardHolderName(userRequest.getCardHolderName());
        cardCreatedEvent.setBalance(userRequest.getBalance());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE,RabbitConfig.ACCOUNT_CREATED,cardCreatedEvent);;
    }
    public void CardDeactivatedEvent(CardDeactivatedEvent cardDeactivatedEvent ) {
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE,RabbitConfig.ACCOUNT_DEACTIVATED,cardDeactivatedEvent);
    }
}
