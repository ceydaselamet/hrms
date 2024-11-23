package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.helpers.emailValidation.EmailService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {

    private UserDao userDao;
    private EmailService emailService;

    public UserManager(UserDao userDao, EmailService emailService) {
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<List<User>>(userDao.findAll());
    }

    @Override
    public DataResult<User> getByEmail(String email) {
        User user = userDao.findByEmail(email);
        if (user != null) {
            return new SuccessDataResult<User>(user);
        }
        return new ErrorDataResult<User>("User not found");
    }

    @Override
    public Result save(User user) {
        Result businessRules = BusinessRules.run(checkEmailVerification(user));
        if(businessRules!=null) return businessRules;
        userDao.save(user);
        return new SuccessResult();
    }

    @Override
    public Result verifyUser(String email, int emailVerifyCode) {
        User user = userDao.findByEmailAndEmailVerifyCode(email, emailVerifyCode);
        if (user != null) {
            user.setEmailVerified(true);
            userDao.save(user);
            emailService.send(email, "Email Verification", "Your email has been verified successfully.");
            return new SuccessResult("User verified successfully.");
        }
        return new ErrorResult("Verification failed.");
    }

    private Result checkEmailVerification(User user) {
        if (user == null) {
            return new ErrorResult("User is null.");
        }

        if (user.isEmailVerified()) {
            return new SuccessResult();
        } else {
            return new ErrorResult("Email is not verified. User cannot be saved.");
        }
    }

}
