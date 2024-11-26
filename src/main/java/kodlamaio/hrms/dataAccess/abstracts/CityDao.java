package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CityDao extends JpaRepository<City, Integer> {
    Optional<City> findById(int id);
}
