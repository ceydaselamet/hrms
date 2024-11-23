package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobPositionDao extends JpaRepository<JobPosition, UUID> {
    JobPosition findByJobPositionName(String jobPositionName);
}
