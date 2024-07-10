package kz.smartrecs.authorization.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kz.smartrecs.authorization.validator.ValidEmail;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(schema = "public", name = "customer")
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String middleName;

    @NotBlank
    @ValidEmail
    private String email;

    @NotNull
    private String mobileNumber;

    @NotBlank
    private String pwd;

    @NotBlank
    private String role;

    @NotNull
    private Boolean isActive;

    private Date createDt;
}
