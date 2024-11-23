package kodlamaio.hrms.core.utilities.helpers.identityValidation;

import kodlamaio.hrms.core.utilities.results.Result;

import java.util.Date;

public interface FakeMernisValidationService {
    Result validate(String nationalyId);
}
