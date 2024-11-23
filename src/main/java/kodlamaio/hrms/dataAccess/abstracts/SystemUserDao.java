package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SystemUserDao extends JpaRepository<SystemUser, UUID> {
}
