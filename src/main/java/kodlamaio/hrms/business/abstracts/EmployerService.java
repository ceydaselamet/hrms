package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dtos.EmployerForLoginDto;
import kodlamaio.hrms.entities.dtos.EmployerForRegisterDto;

import java.util.List;
import java.util.UUID;

public interface EmployerService {
    DataResult<List<Employer>> getlAll();
    DataResult<Employer> getById(UUID id);
    Result register(EmployerForRegisterDto employerForRegisterDto);
    Result login(EmployerForLoginDto employerForLoginDto);
}
