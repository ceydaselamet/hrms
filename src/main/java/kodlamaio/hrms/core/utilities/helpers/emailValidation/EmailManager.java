package kodlamaio.hrms.core.utilities.helpers.emailValidation;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

@Service
public class EmailManager implements EmailService{
    @Override
    public Result send(String to, String title, String message) {
        return new SuccessResult("Email doğrulaması başarılı.");
    }
}
