package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDao extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    User findByEmailAndEmailVerifyCode(String email, int emailVerifyCode);
}
