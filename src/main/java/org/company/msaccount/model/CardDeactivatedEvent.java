package org.company.msaccount.model;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDeactivatedEvent {

    @Column(nullable = false, unique = true, length = 16)
    private String cardNumber;

    @Column(length = 3)
    private String cvv;
}
