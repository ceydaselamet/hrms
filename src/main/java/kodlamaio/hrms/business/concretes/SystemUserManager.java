package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.SystemUserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.SystemUserDao;
import kodlamaio.hrms.entities.concretes.SystemUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemUserManager implements SystemUserService {

    private SystemUserDao systemUserDao;

    public SystemUserManager(SystemUserDao systemUserDao) {
        super();
        this.systemUserDao = systemUserDao;
    }

    @Override
    public DataResult<List<SystemUser>> getAll() {
        return new SuccessDataResult<List<SystemUser>>(systemUserDao.findAll());
    }
}
