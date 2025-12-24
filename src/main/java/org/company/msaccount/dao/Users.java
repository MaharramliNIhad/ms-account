package org.company.msaccount.dao;

import jakarta.persistence.*;
import lombok.*;
import org.company.msaccount.enums.ActiveStatus;

import java.math.BigDecimal;


@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String surname;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActiveStatus active;

}
