package vti.dtn.account_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
}
