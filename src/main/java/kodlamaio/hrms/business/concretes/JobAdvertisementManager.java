package kodlamaio.hrms.business.concretes;


import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementForAddDto;
import kodlamaio.hrms.entities.dtos.JobAdvertisementForListingDto;
import kodlamaio.hrms.entities.dtos.converters.JobAdvertisementConverter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {
    private final JobAdvertisementDao jobAdvertisementDao;
    private final JobAdvertisementConverter jobAdvertisementConverter;

    public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao,
                                   JobAdvertisementConverter jobAdvertisementConverter) {
        this.jobAdvertisementDao = jobAdvertisementDao;
        this.jobAdvertisementConverter = jobAdvertisementConverter;
    }

    @Override
    public DataResult<JobAdvertisement> findById(UUID id) {
        return new SuccessDataResult<JobAdvertisement>(jobAdvertisementDao.findById(id).orElse(null));
    }

    @Override
    public DataResult<List<JobAdvertisementForListingDto>> findByIsActiveTrue() {
        List<JobAdvertisement> jobAdvertisements = this.jobAdvertisementDao.findByIsActiveTrue();
        List<JobAdvertisementForListingDto> dtos = jobAdvertisements
                .stream()
                .map(jobAdvertisementConverter::convertToDto)
                .collect(Collectors.toList());
        return new SuccessDataResult<List<JobAdvertisementForListingDto>>(dtos);
    }

    @Override
    public DataResult<List<JobAdvertisementForListingDto>> findByIsActiveTrueAndIsApprovedTrue() {
        List<JobAdvertisement> jobAdvertisements = this.jobAdvertisementDao.findByIsActiveTrueAndIsApprovedTrue();
        List<JobAdvertisementForListingDto> dtos = jobAdvertisements
                .stream()
                .map(jobAdvertisementConverter::convertToDto)
                .collect(Collectors.toList());
        return new SuccessDataResult<List<JobAdvertisementForListingDto>>(dtos);
    }

    @Override
    public DataResult<List<JobAdvertisementForListingDto>> findByIsActiveTrueAndEmployer_Id(UUID id) {
        List<JobAdvertisement> jobAdvertisements = jobAdvertisementDao.findByIsActiveTrueAndEmployer_Id(id);
        List<JobAdvertisementForListingDto> dtos = jobAdvertisements
                .stream()
                .map(jobAdvertisementConverter::convertToDto)
                .collect(Collectors.toList());
        return new SuccessDataResult<List<JobAdvertisementForListingDto>>(dtos);
    }

    @Override
    public DataResult<List<JobAdvertisementForListingDto>> findByIsActiveTrueOrderByCreateDate() {
        List<JobAdvertisement> jobAdvertisements = jobAdvertisementDao.findByIsActiveTrueOrderByCreateDate();
        List<JobAdvertisementForListingDto> dtos = jobAdvertisements
                .stream()
                .map(jobAdvertisementConverter::convertToDto)
                .collect(Collectors.toList());
        return new SuccessDataResult<List<JobAdvertisementForListingDto>>(dtos);
    }

    @Override
    public Result add(JobAdvertisementForAddDto jobAdvertisementForAddDto) {
        Result businessRules = BusinessRules.run(
                checkIfJobDescriptionEmpty(jobAdvertisementForAddDto.getJobDescription()),
                checkLastApplyDateNotBeforeNow(jobAdvertisementForAddDto.getLastApplyDate())
        );
        JobAdvertisement jobAdvertisement = jobAdvertisementConverter.convertToEntity(jobAdvertisementForAddDto);
        jobAdvertisementDao.save(jobAdvertisement);

        return new SuccessResult("Job Advertisement successfully added");
    }

    @Override
    public Result changeTheActivity(UUID advertisementId, UUID employerId) {
        JobAdvertisement jobAdvertisement = jobAdvertisementDao.findById(advertisementId)
                .orElse(null);
        if (jobAdvertisement == null) {
            return new SuccessResult("Job Advertisement not found");
        }
        boolean newStatus = !jobAdvertisement.isActive();
        jobAdvertisement.setActive(newStatus);
        jobAdvertisementDao.save(jobAdvertisement);

        return new SuccessResult("Activity: " + (newStatus? "active": "passive"));
    }

    private Result checkIfJobDescriptionEmpty(String jobDescription){
        if(jobDescription == null)
            return new ErrorResult("Job Description can not be empty");
        return new SuccessResult();
    }

    private Result checkLastApplyDateNotBeforeNow(Date lastApplyDate) {
        if (lastApplyDate.before(new Date()))
            return new ErrorResult("Last apply date cannot be before the current date.");
        return new SuccessResult();
    }

}
