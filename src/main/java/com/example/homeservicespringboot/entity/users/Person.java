package com.example.homeservicespringboot.entity.users;


import com.example.homeservicespringboot.entity.BaseEntity;
import com.example.homeservicespringboot.entity.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalTime;


@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Person extends BaseEntity<Long> {


    @Column(nullable = false)
    @NotEmpty(message = "firstname should not contains space")
    private String firstname;
    @Column(nullable = false)
    @NotEmpty(message = "lastname should not contains space")
    private String lastname;

    @Column(unique = true,nullable = false)
    @Email(message = "email pattern not valid ")
    @NotBlank(message = "email should not contains null value or space . ")
    private String email;

    @Column(unique = true,nullable = false)
    @NotBlank(message = "username should not contains null value or space . ")
    @Pattern(regexp = "^[A-Za-z0-9._]$")
    private String username;
    @Column(nullable = false)
    @NotBlank(message = "password should not contains null value or space . ")
    @Pattern(regexp = "^[A-Za-z0-9._$%^&*#!@\\-/\\\\]{8,}$")
    private String password;

    @CreationTimestamp
    private LocalDate dateOfSignup;

    @CreationTimestamp
    private LocalTime timeOfSignup;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public Person(String firstname, String lastname, String email, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
