package kz.smartrecs.authorization.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Entity
@Table(schema = "public", name = "user_account")
@Data
@NoArgsConstructor
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String middleName;

    @NonNull
    private String email;

    @NonNull
    private String pwd;

    @NonNull
    private String role;

    @NonNull
    private Boolean isActive;

}
