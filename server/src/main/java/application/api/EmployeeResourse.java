package application.api;

import application.domain.ModelEmployee;
import application.domain.ModelPosition;
import application.repo.EmployeeRepo;
import application.repo.PositionRepo;
import application.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeResourse {
    private final EmployeeService employeeService;
    private final EmployeeRepo employeeRepo;
    private final PositionRepo positionRepo;

    @GetMapping("/all")
    public ResponseEntity<List<ModelEmployee>> getEmployees() {
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }
    @PostMapping("/save")
    public ResponseEntity<ModelEmployee> saveEmployee(@RequestBody
                                                      ModelEmployee employee) {
        return ResponseEntity.ok().body(employeeService.saveEmployee(employee));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ModelEmployee> deleteEmployee(@RequestBody
                                                        ModelEmployee employee) {
        employeeService.deletePostion(employee.getId());
        return ResponseEntity.ok().body(employee);
    }
    @PutMapping("/upd_name")
    public ResponseEntity<String> updName(@RequestBody sever.api.utils.UpdName data) {
        employeeService.updEmployeeName(data.getName(), data.getNew_name());
        return ResponseEntity.ok().body(data.getNew_name());
    }
    @PutMapping("/upd_lastname")
    public ResponseEntity<String> updLastname(@RequestBody sever.api.utils.UpdLastName data) {
        employeeService.updEmployeeLastName(data.getLastname(), data.getNew_lastname());
        return ResponseEntity.ok().body(data.getNew_lastname());
    }

    @PutMapping("/upd_dob")
    public ResponseEntity<String> updLastname(@RequestBody sever.api.utils.UpdDob data) {
        employeeService.updEmployeeDob(data.getDob(), data.getNew_dob());
        log.info("dob {} was updated for {}", data.getDob(), data.getNew_dob());
        return ResponseEntity.ok().body(data.getNew_dob());
    }

    @PostMapping("/position_to_employee")
    public ResponseEntity<ModelPosition> savePositionToEmployee(@RequestBody sever.api.utils.PositionToEmpl data) {
        log.info(" employeeId: {}", data.getEmployeeId());
        employeeService.addPositionToEmployee(data.getEmployeeId(), data.getPositionId());
        return ResponseEntity.ok().body(positionRepo.find(data.getPositionId()));
    }

}
