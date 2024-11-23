package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployerDao extends JpaRepository<Employer, UUID> {
    Employer findByUser(User user);
}
