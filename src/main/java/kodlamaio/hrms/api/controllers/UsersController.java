package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin
public class UsersController {
    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getAll")
    public DataResult<List<User>> getAll(){
        return userService.getAll();
    }

    @PostMapping("verify")
    public Result verify(String email, int verifyCode){
        return userService.verifyUser(email, verifyCode);
    }
}
