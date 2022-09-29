package uz.zako.oquv_markaz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.oquv_markaz.payload.CenterBranchesPayload;
import uz.zako.oquv_markaz.payload.EmployePayload;
import uz.zako.oquv_markaz.service.CenterBranchesService;
import uz.zako.oquv_markaz.service.EmployeeService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/creator/v1")
public class MenegerController {

    private final EmployeeService employeeService;
    private final CenterBranchesService centerBranchesService;

    @PostMapping("/save/employee/{hashId}")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployePayload payload, @PathVariable("hashId") String id) {
        return employeeService.save(payload, id);
    }

    @PutMapping("/edit/employee/{hashId}")
    public ResponseEntity<?> editEmployee(@RequestBody EmployePayload payload, @PathVariable("hashId") String id) {
        return employeeService.editEmploye(payload, id);
    }

    @DeleteMapping("/delete/employee/{emloyeId}")
    public ResponseEntity<?> deleteEmploye(@PathVariable("employeId") Long id) {
        return employeeService.deleteEmploye(id);
    }

    @DeleteMapping("/delete/employee/{centerBranchesId}")
    public ResponseEntity<?> deleteEmployeeCenterBranchesId(@PathVariable("centerBranchesId") Long id) {
        return employeeService.deleteEmployeCenterBranchesId(id);
    }

    @GetMapping("/getAll/employee")
    public ResponseEntity<?> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/getCenterBranchesId/employee/{centerBranchesId}")
    public ResponseEntity<?> getemployeCenterBranchesId(@PathVariable("centerBranchesId") Long id) {
        return employeeService.getEmployeCenterBranchesId(id);
    }

    @GetMapping("/getCenterBranches/token")
    public ResponseEntity<?> getCenterBranchesToken(){
        return centerBranchesService.getCenterBranchesTokenId();
    }




}

