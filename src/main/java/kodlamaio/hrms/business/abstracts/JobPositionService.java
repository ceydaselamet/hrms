package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosition;

import java.util.List;
import java.util.UUID;

public interface JobPositionService {
    DataResult<List<JobPosition>> getAll();
    DataResult<JobPosition> getById(UUID id);
    DataResult<JobPosition> getByJobPositionName(String jobPositionName);

    Result add(JobPosition jobPosition);

}
