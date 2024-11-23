package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.helpers.identityValidation.FakeMernisValidationService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.EmployeeDao;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.entities.dtos.EmployeeForLoginDto;
import kodlamaio.hrms.entities.dtos.EmployeeForRegisterDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeManager implements EmployeeService {

    private EmployeeDao employeeDao;
    private FakeMernisValidationService fakeMernisValidationService;
    private UserService userService;

    public EmployeeManager(EmployeeDao employeeDao,
                           FakeMernisValidationService fakeMernisValidationService,
                           UserService userService) {
        this.employeeDao = employeeDao;
        this.fakeMernisValidationService = fakeMernisValidationService;
        this.userService = userService;
    }

    @Override
    public DataResult<List<Employee>> getlAll() {
        return new SuccessDataResult<List<Employee>>(employeeDao.findAll(), "Employees listed.");
    }

    @Override
    public DataResult<Employee> getById(UUID id) {
        Optional<Employee> employee = employeeDao.findById(id);
        if(employee.isPresent())
            return new SuccessDataResult<Employee>(employee.get());
        return new ErrorDataResult<Employee>("Employee not found");
    }

    @Override
    public Result register(EmployeeForRegisterDto employeeForRegisterDto) {
        Result businessRules = BusinessRules.run(
                checkMernisVerification(employeeForRegisterDto.getNationalityId()),
                isUserExistWithNationalityId(employeeForRegisterDto.getNationalityId()),
                isUserExistWithEmail(employeeForRegisterDto.getEmail()),
                isPasswordMatch(employeeForRegisterDto.getPassword(),
                        employeeForRegisterDto.getVerifyPassword())
        );
        if (!businessRules.isSuccess()) {
            return businessRules;
        }

        User user = new User(
                employeeForRegisterDto.getPassword(),
                0,
                false,
                employeeForRegisterDto.getEmail()
        );

        userService.save(user);

        Employee employee = new Employee(
                employeeForRegisterDto.getFirstName(),
                employeeForRegisterDto.getLastName(),
                employeeForRegisterDto.getNationalityId(),
                employeeForRegisterDto.getDateOfBirth(),
                user
        );

        employeeDao.save(employee);

        return new SuccessResult("Employee registered successfully.");
    }

    @Override
    public Result login(EmployeeForLoginDto employeeForLoginDto) {
        Result businessRules = BusinessRules.run(
                isPasswordTrue(employeeForLoginDto.getEmail(), employeeForLoginDto.getPassword())
        );

        if (!businessRules.isSuccess()) {
            return businessRules;
        }

        return new SuccessResult("Login successful.");
    }


    private Result isPasswordMatch(String password, String verifyPassword){
        if(password.equals(verifyPassword))
            return new SuccessResult();
        return new ErrorResult("Passwords did not match.");
    }

    private Result isUserExistWithEmail(String email){
        if(userService.getByEmail(email).getData() != null )
            return new ErrorResult("User already exists");
        return new SuccessResult();
    }

    private Result isUserExistWithNationalityId(String nationalityId){
        if(employeeDao.findByNationalityId(nationalityId)  != null)
            return new ErrorResult("Nationaly ID exists");
        return new SuccessResult();
    }

    private Result checkMernisVerification(String nationalityId) {
        return fakeMernisValidationService.validate(nationalityId);
    }

    private Result isPasswordTrue(String email, String password) {
        User user = userService.getByEmail(email).getData();

        if (user == null) {
            return new ErrorResult("User not found.");
        }

        if (user.getPassword().equals(password)) {
            return new SuccessResult();
        } else {
            return new ErrorResult("Incorrect password.");
        }
    }


}
