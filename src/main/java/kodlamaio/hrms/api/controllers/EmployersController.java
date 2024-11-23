package kodlamaio.hrms.api.controllers;

import jakarta.validation.Valid;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dtos.EmployerForLoginDto;
import kodlamaio.hrms.entities.dtos.EmployerForRegisterDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employers/")
@CrossOrigin
public class EmployersController {
    private EmployerService employerService;

    public EmployersController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("getAll")
    public DataResult<List<Employer>> getAll(){
        return employerService.getlAll();
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody EmployerForRegisterDto employerForRegisterDto){
        return ResponseEntity.ok(employerService.register(employerForRegisterDto));
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody EmployerForLoginDto employerForLoginDto){
        return ResponseEntity.ok(employerService.login(employerForLoginDto));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException
            (MethodArgumentNotValidException exceptions){
        Map<String,String> validationErrors = new HashMap<String, String>();
        for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors
                = new ErrorDataResult<Object>(validationErrors,"Validation errors");
        return errors;
    }
}
