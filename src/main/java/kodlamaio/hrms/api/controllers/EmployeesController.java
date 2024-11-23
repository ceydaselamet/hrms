package kodlamaio.hrms.api.controllers;

import jakarta.validation.Valid;
import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.dtos.EmployeeForLoginDto;
import kodlamaio.hrms.entities.dtos.EmployeeForRegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees/")
@CrossOrigin
public class EmployeesController {
    private EmployeeService employeeService;

    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("getAll")
    public DataResult<List<Employee>> getAll(){
        return employeeService.getlAll();
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody EmployeeForRegisterDto employeeForRegisterDto){
        return ResponseEntity.ok(employeeService.register(employeeForRegisterDto));
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody EmployeeForLoginDto employeeForLoginDto){
        var result = employeeService.login(employeeForLoginDto);
        return ResponseEntity.ok(result);
    }
}
