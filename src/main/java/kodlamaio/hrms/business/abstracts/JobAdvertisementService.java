package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.concretes.JobPosition;
import kodlamaio.hrms.entities.dtos.JobAdvertisementForAddDto;
import kodlamaio.hrms.entities.dtos.JobAdvertisementForListingDto;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.UUID;

public interface JobAdvertisementService {
    DataResult<JobAdvertisement> findById(UUID id);
    DataResult<List<JobAdvertisementForListingDto>> findByIsActiveTrue();
    DataResult<List<JobAdvertisementForListingDto>> findByIsActiveTrueAndIsApprovedTrue();
    DataResult<List<JobAdvertisementForListingDto>> findByIsActiveTrueAndEmployer_Id(UUID id);
    DataResult<List<JobAdvertisementForListingDto>> findByIsActiveTrueOrderByCreateDate();

    Result add(JobAdvertisementForAddDto jobAdvertisementForAddDto);
    Result changeTheActivity(UUID advertisementId, UUID employerId);
}
