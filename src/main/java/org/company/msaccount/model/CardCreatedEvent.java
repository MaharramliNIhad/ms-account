package org.company.msaccount.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardCreatedEvent {
    String cardHolderName;
    BigDecimal balance;

}
