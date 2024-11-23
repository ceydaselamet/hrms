package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "emailVerified", nullable = false)
    private boolean emailVerified;

    @Column(name = "emailVerifyCode", nullable = false)
    private int emailVerifyCode;

    public User(String password, int emailVerifyCode, boolean emailVerified, String email) {
        this.password = password;
        this.emailVerifyCode = emailVerifyCode;
        this.emailVerified = emailVerified;
        this.email = email;
    }


}
