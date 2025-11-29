package org.company.msaccount.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String name;
    private String surname;
    private String email;
}
