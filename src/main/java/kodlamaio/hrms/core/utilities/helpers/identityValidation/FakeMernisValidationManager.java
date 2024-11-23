package kodlamaio.hrms.core.utilities.helpers.identityValidation;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FakeMernisValidationManager implements FakeMernisValidationService{

    @Override
    public Result validate(String nationalyId) {
        return new SuccessResult("Mernis doğrulaması başarılı");
    }
}
