package kodlamaio.hrms.entities.dtos.converters;

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementForAddDto;
import kodlamaio.hrms.entities.dtos.JobAdvertisementForListingDto;
import org.springframework.stereotype.Component;

@Component
public class JobAdvertisementConverter {
    private final EmployerService employerService;
    private final JobPositionService jobPositionService;
    private final CityService cityService;

    public JobAdvertisementConverter(EmployerService employerService,
                                     JobPositionService jobPositionService,
                                     CityService cityService) {
        this.employerService = employerService;
        this.jobPositionService = jobPositionService;
        this.cityService = cityService;
    }

    public JobAdvertisementForListingDto convertToDto(JobAdvertisement jobAdvertisement) {
        return new JobAdvertisementForListingDto(
                jobAdvertisement.getId(),
                jobAdvertisement.getEmployer().getCompanyName(),
                jobAdvertisement.getJobPosition().getJobPositionName(),
                jobAdvertisement.getOpenPositionCount(),
                jobAdvertisement.getCreateDate(),
                jobAdvertisement.getLastApplyDate()
        );
    }

    public JobAdvertisement convertToEntity(JobAdvertisementForAddDto jobAdvertisementForAddDto) {
        JobAdvertisement jobAdvertisement = new JobAdvertisement();

        // Dtos to entity
        jobAdvertisement.setJobDescription(jobAdvertisementForAddDto.getJobDescription());
        jobAdvertisement.setMinSalary(jobAdvertisementForAddDto.getMinSalary());
        jobAdvertisement.setMaxSalary(jobAdvertisementForAddDto.getMaxSalary());
        jobAdvertisement.setOpenPositionCount(jobAdvertisementForAddDto.getOpenPositionCount());
        jobAdvertisement.setLastApplyDate(jobAdvertisementForAddDto.getLastApplyDate());
        jobAdvertisement.setActive(jobAdvertisementForAddDto.isActive());
        jobAdvertisement.setCityId(jobAdvertisementForAddDto.getCityId());

        jobAdvertisement.setEmployer(employerService.getById(jobAdvertisementForAddDto.getEmployerId()).getData());
        jobAdvertisement.setJobPosition(jobPositionService.getById(jobAdvertisementForAddDto.getJobPositionId()).getData());

        return jobAdvertisement;
    }
}
