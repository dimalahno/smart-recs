package kz.smartrecs.authorization.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kz.smartrecs.authorization.validator.ValidEmail;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(schema = "public", name = "user_account")
@Data
@NoArgsConstructor
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String middleName;

    @NotBlank
    @ValidEmail
    private String email;

    @NotBlank
    private String pwd;

    @NotBlank
    private String role;

    @NotNull
    private Boolean isActive;

}
