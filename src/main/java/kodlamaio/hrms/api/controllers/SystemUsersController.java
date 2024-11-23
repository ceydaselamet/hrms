package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.SystemUserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.SystemUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/systemUsers/")
@CrossOrigin
public class SystemUsersController {
    private SystemUserService systemUserService;

    public SystemUsersController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @GetMapping("getAll")
    public DataResult<List<SystemUser>> getAll(){
        return systemUserService.getAll();
    }
}
