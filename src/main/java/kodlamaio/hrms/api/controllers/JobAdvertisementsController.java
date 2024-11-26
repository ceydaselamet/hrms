package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementForAddDto;
import kodlamaio.hrms.entities.dtos.JobAdvertisementForListingDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jobAdvertisements/")
@CrossOrigin
public class JobAdvertisementsController {
    private JobAdvertisementService jobAdvertisementService;

    public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @GetMapping("getById")
    public DataResult<JobAdvertisement> findById(@RequestParam UUID id) {
        return jobAdvertisementService.findById(id);
    }

    @GetMapping("getAllActive")
    public DataResult<List<JobAdvertisementForListingDto>> findByIsActiveTrue() {
        return jobAdvertisementService.findByIsActiveTrue();
    }

    @GetMapping("getAllActiveAndApproved")
    public DataResult<List<JobAdvertisementForListingDto>> findByIsActiveTrueAndIsApprovedTrue() {
        return jobAdvertisementService.findByIsActiveTrueAndIsApprovedTrue();
    }

    @GetMapping("getActiveByEmployer")
    public DataResult<List<JobAdvertisementForListingDto>> findByIsActiveTrueAndEmployer_Id(@RequestParam UUID id) {
        return jobAdvertisementService.findByIsActiveTrueAndEmployer_Id(id);
    }

    @GetMapping("getAllActiveOrderedByDate")
    public DataResult<List<JobAdvertisementForListingDto>> findByIsActiveTrueOrderByCreateDate() {
        return jobAdvertisementService.findByIsActiveTrueOrderByCreateDate();
    }

    @PostMapping("add")
    public Result add(@RequestBody JobAdvertisementForAddDto jobAdvertisementForAddDto) {
        return jobAdvertisementService.add(jobAdvertisementForAddDto);
    }

    @PutMapping("changeActivityStatus")
    public Result changeTheActivity(@RequestParam UUID advertisementId, @RequestParam UUID employerId) {
        return jobAdvertisementService.changeTheActivity(advertisementId, employerId);
    }
}
