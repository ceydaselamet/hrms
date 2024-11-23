package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeDao extends JpaRepository<Employee, UUID> {
    Employee findByNationalityId(String nationalityId);
}
