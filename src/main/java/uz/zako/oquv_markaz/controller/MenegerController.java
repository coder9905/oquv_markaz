package uz.zako.oquv_markaz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.oquv_markaz.entity.Role;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.CenterBranchesPayload;
import uz.zako.oquv_markaz.payload.EmployePayload;
import uz.zako.oquv_markaz.payload.WorkerPayload;
import uz.zako.oquv_markaz.repository.RoleRepository;
import uz.zako.oquv_markaz.service.CenterBranchesService;
import uz.zako.oquv_markaz.service.EmployeeService;
import uz.zako.oquv_markaz.service.WorkerService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/creator/v1")
public class MenegerController {

    private final EmployeeService employeeService;
    private final CenterBranchesService centerBranchesService;
    private final RoleRepository roleRepository;
    private final WorkerService workerService;

    @PostMapping("/save/employee")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployePayload payload) {
        return employeeService.save(payload);
    }

    @PutMapping("/edit/employee")
    public ResponseEntity<?> editEmployee(@RequestBody EmployePayload payload) {
        return employeeService.editEmploye(payload);
    }

    @DeleteMapping("/delete/employee/{employeId}")
    public ResponseEntity<?> deleteEmploye(@PathVariable("employeId") Long id) {
        return employeeService.deleteEmploye(id);
    }

    @GetMapping("/getAll/employee")
    public ResponseEntity<?> getAllEmployee() {
        return employeeService.getAllEmployee();
    }


    @GetMapping("/getCenterBranchesPageId/employee/{centerBranchesId}")
    public ResponseEntity<?> getemployeCenterBranchesId(@RequestParam(defaultValue = "0") int page, int size,@PathVariable("centerBranchesId") Long id) {
        return employeeService.getEmployeCenterBranchesId(size,page,id);
    }

    @GetMapping("/getCenterBranchesId/employee/{centerBranchesId}")
    public ResponseEntity<?> getemployeCenterBranchesAllId(@PathVariable("centerBranchesId") Long id) {
        return employeeService.getEmployeCenterBranchesAllId(id);
    }



    @GetMapping("/getCenterBranches/token")
    public ResponseEntity<?> getCenterBranchesToken(){
        return centerBranchesService.getCenterBranchesTokenId();
    }

    @GetMapping("/all/roles")
    public ResponseEntity<?> getAllRole(){
        try {
            List<Role> roles=new ArrayList<>();
            roles=roleRepository.findAll();
            if (roles!=null){
                return ResponseEntity.ok(Result.ok(roles));
            }
            return new ResponseEntity(new Result(false,"error get All Role",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(Result.error("getAll Role error"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllCenterBranches/trainingCenterId/{trainingCenterId}")
    public ResponseEntity<?> getCenterBranchesTrainingCenterId(@PathVariable("trainingCenterId") Long id){
        return centerBranchesService.getAllCenterBranchesTrainingCenterId(id);
    }


    @PostMapping("/save/worker")
    public ResponseEntity<?> saveWorker(@RequestBody WorkerPayload payload) {
        return workerService.save(payload);
    }

    @PutMapping("/edit/worker")
    public ResponseEntity<?> editWorker(@RequestBody WorkerPayload payload) {
        return workerService.editWorker(payload);
    }

    @DeleteMapping("/delete/worker/{workerId}")
    public ResponseEntity<?> deleteWorker(@PathVariable("workerId") Long id) {
        return workerService.deleteWorker(id);
    }

    @GetMapping("/getAll/worker")
    public ResponseEntity<?> getAllWorker() {
        return workerService.getAllWorker();
    }


    @GetMapping("/get/worker/{workerId}")
    public ResponseEntity<?> getWorkerId(@PathVariable("workerId") Long id){
        return workerService.getOneWorkerId(id);
    }

    @GetMapping("/getCenterBranchesId/worker/{centerBranchesId}")
    public ResponseEntity<?> getWorkerCenterBranchesId(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "0") int size,@PathVariable("centerBranchesId") Long id) {
        return workerService.getWorkerCenterBranchesId(size,page,id);
    }

    @GetMapping("/getCenterBranchesAllId/worker/{centerBranchesId}")
    public ResponseEntity<?> getWorkerCenterBranchesId(@PathVariable("centerBranchesId") Long id) {
        return workerService.getWorkerCenterBranchesAllId(id);
    }



}

