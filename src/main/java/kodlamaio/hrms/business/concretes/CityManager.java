package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.entities.concretes.City;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CityManager implements CityService {
    private CityDao cityDao;

    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>(cityDao.findAll());
    }

    @Override
    public DataResult<City> getById(int id) {
        return new SuccessDataResult<City>(cityDao.findById(id).orElse(null));
    }
}
