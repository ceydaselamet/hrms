package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.dtos.EmployeeForLoginDto;
import kodlamaio.hrms.entities.dtos.EmployeeForRegisterDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    DataResult<List<Employee>> getlAll();
    DataResult<Employee> getById(UUID id);
    Result register(EmployeeForRegisterDto employeeForRegisterDto);
    Result login(EmployeeForLoginDto employeeForLoginDto);
}
