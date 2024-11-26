package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.City;
import kodlamaio.hrms.entities.concretes.JobPosition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobPositionManager implements JobPositionService {
    private JobPositionDao jobPositionDao;

    public JobPositionManager(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    @Override
    public DataResult<List<JobPosition>> getAll() {
        return new SuccessDataResult<List<JobPosition>>(jobPositionDao.findAll());
    }

    @Override
    public DataResult<JobPosition> getById(UUID id) {
        return new SuccessDataResult<JobPosition>(jobPositionDao.findById(id).orElse(null));
    }

    @Override
    public DataResult<JobPosition> getByJobPositionName(String jobPositionName) {
        return new SuccessDataResult<JobPosition>(jobPositionDao.findByJobPositionName(jobPositionName));
    }

    @Override
    public Result add(JobPosition jobPosition) {
        Result businessRules = BusinessRules.run(
                checkIfJobPositionExists(jobPosition.getJobPositionName())
        );

        if (!businessRules.isSuccess()) {
            return businessRules;
        }

        jobPositionDao.save(jobPosition);
        return new SuccessResult("Job position added successfully.");
    }

    private Result checkIfJobPositionExists(String jobPosition){
        JobPosition existingJobPosition = jobPositionDao.findByJobPositionName(jobPosition);
        if(existingJobPosition != null)
            return new ErrorResult("Job position already exists.");
        return new SuccessResult();
    }
}
