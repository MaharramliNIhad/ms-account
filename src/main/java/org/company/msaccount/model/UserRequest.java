package org.company.msaccount.model;

import lombok.*;
import org.company.msaccount.enums.ActiveStatus;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String active;
}
