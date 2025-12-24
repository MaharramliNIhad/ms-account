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
public class CardRequest {
    private String cardHolderName;
    private String cardNumber;
    private String cvv;
    private String expirationDate;
    private BigDecimal balance;
}
