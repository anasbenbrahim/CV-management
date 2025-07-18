package me.project.cvservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private Long id ;
    private String email;
    private String firstName ;
    private String lastName;
    private String phoneNumber;
    private String address;
}
