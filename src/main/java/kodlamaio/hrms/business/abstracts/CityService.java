package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.City;

import java.util.List;
import java.util.UUID;

public interface CityService {
    DataResult<List<City>> getAll();
    DataResult<City> getById(int id);
}
