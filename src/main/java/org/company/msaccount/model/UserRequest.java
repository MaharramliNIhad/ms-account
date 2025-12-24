package org.company.msaccount.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.company.msaccount.enums.ActiveStatus;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50)
    private String name;
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50)
    private String surname;
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 10, max = 70)
    private String email;
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 8, max =20)
    private String password;
    private BigDecimal balance;
    private String CardHolderName;
    private String active;

}
