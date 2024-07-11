package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class Contacts {
    private String Name;
    private String LastName;
    private String Phone;
    private String email;
    private String Address;
    private String description;

}
