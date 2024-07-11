package kz.smartrecs.authorization.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    /**
     * Имя
     */
    @NotBlank
    private String firstName;

    /**
     * Фамилия
     */
    @NotBlank
    private String lastName;

    /**
     * Отчество
     */
    private String middleName;

    @NotBlank
    @ValidEmail
    private String email;

    @NotBlank
    private String mobileNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;

    private String role;

    private Boolean isActive;

    private Date createDt;
}
