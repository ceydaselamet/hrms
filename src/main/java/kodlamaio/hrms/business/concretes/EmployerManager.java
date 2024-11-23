package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.entities.dtos.EmployerForLoginDto;
import kodlamaio.hrms.entities.dtos.EmployerForRegisterDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployerManager implements EmployerService {
    private EmployerDao employerDao;
    private UserService userService;

    public EmployerManager(EmployerDao employerDao, UserService userService) {
        this.employerDao = employerDao;
        this.userService = userService;
    }


    @Override
    public DataResult<List<Employer>> getlAll() {
        return new SuccessDataResult<List<Employer>>(employerDao.findAll());
    }

    @Override
    public DataResult<Employer> getById(UUID id) {
        Optional<Employer> employer = employerDao.findById(id);
        if(employer.isPresent())
            return new SuccessDataResult<Employer>(employer.get());
        return new ErrorDataResult<Employer>("Employer not found");
    }

    @Transactional
    @Override
    public Result register(EmployerForRegisterDto employerForRegisterDto) {

        Result businessRules = BusinessRules.run(
                isUserExistWithEmail(employerForRegisterDto.getEmail()),
                isPasswordsMatch(employerForRegisterDto.getPassword(), employerForRegisterDto.getVerifyPassword())
        );

        if (!businessRules.isSuccess()) {
            return businessRules;
        }

        User user = new User(
                employerForRegisterDto.getPassword(),
                0,
                false,
                employerForRegisterDto.getEmail()
        );

        userService.save(user);

        Employer employer = new Employer(
                UUID.randomUUID(),
                employerForRegisterDto.getCompanyName(),
                employerForRegisterDto.getPhoneNumber(),
                employerForRegisterDto.getWebsite(),
                false,
                user
        );

        employerDao.save(employer);

        return new SuccessResult("Employer registered successfully.");
    }

    @Override
    public Result login(EmployerForLoginDto employerForLoginDto) {

        Result businessRules = BusinessRules.run(
                isPasswordTrue(employerForLoginDto.getEmail(), employerForLoginDto.getPassword())
        );

        if (!businessRules.isSuccess()) {
            return businessRules;
        }

        User user = userService.getByEmail(employerForLoginDto.getEmail()).getData();
        Employer employer = employerDao.findByUser(user);

        if (employer == null) {
            return new ErrorResult("Employer not found.");
        }

        Result approvalCheck = isEmployerApproved(employer);
        if (!approvalCheck.isSuccess()) {
            return approvalCheck;
        }

        return new SuccessResult("Login successful.");
    }

    private Result isPasswordsMatch(String password, String verifyPassword) {
        if (password.equals(verifyPassword)) {
            return new SuccessResult();
        }
        return new ErrorResult("Passwords did not match.");
    }

    private Result isUserExistWithEmail(String email) {
        if (userService.getByEmail(email).getData() != null) {
            return new ErrorResult("Email already exists.");
        }
        return new SuccessResult();
    }

    private Result isPasswordTrue(String email, String password) {
        User user = userService.getByEmail(email).getData();
        if (user == null) {
            return new ErrorResult("User not found.");
        }

        if (user.getPassword().equals(password)) {
            return new SuccessResult();
        }
        return new ErrorResult("Incorrect password.");
    }

    private Result isEmployerApproved(Employer employer){
        if (employer == null) {
            return new ErrorResult("Employer not found.");
        }
        if (employer.isVerifiedBySystem()) {
            return new SuccessResult();
        }
        return new ErrorResult("Employer is not approved by the system");
    }
}
