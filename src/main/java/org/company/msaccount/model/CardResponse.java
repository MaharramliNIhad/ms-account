package org.company.msaccount.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardResponse {
    private Long id;

    private String cardHolderName;

    private String cardNumber;

    private String cvv;

    private String expirationDate;

    private BigDecimal balance;
}
