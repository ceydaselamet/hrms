package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, UUID> {
    List<JobAdvertisement> findByIsActiveTrue();
    List<JobAdvertisement> findByIsActiveTrueOrderByCreateDate();
    List<JobAdvertisement> findByIsActiveTrueAndEmployer_Id(UUID id);
    List<JobAdvertisement> findByIsActiveTrueAndIsApprovedTrue();
}
