package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.concretes.JobPosition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobPositions/")
@CrossOrigin
public class JobPositionsControllers {
    private JobPositionService jobPositionService;

    public JobPositionsControllers(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @GetMapping("getAll")
    public DataResult<List<JobPosition>> getAll(){
        return jobPositionService.getAll();
    }

    @GetMapping("getByJobPositionName")
    public DataResult<JobPosition> getByJobPositionName(String jobPositionName){
        return jobPositionService.getByJobPositionName(jobPositionName);
    }

    @PostMapping("add")
    public Result add(@RequestBody JobPosition jobPosition){
        return jobPositionService.add(jobPosition);
    }

}
