package uz.zako.oquv_markaz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.oquv_markaz.payload.CenterBranchesPayload;
import uz.zako.oquv_markaz.payload.EmployePayload;
import uz.zako.oquv_markaz.payload.TrainingCenterPayload;
import uz.zako.oquv_markaz.payload.UserPayload;
import uz.zako.oquv_markaz.service.CenterBranchesService;
import uz.zako.oquv_markaz.service.EmployeeService;
import uz.zako.oquv_markaz.service.TrainingCenterService;
import uz.zako.oquv_markaz.service.UserService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/creator/v1")
public class CreatorController {

    private final EmployeeService employeeService;
    private final CenterBranchesService centerBranchesService;
    private final TrainingCenterService trainingCenterService;
    private final UserService userService;

    @PostMapping("/saveCenterBranches/{trainingCenterId}")
    public ResponseEntity<?> saveCenterBranches(@RequestBody CenterBranchesPayload payload, @PathVariable("trainingCenterId") Long id){
        return centerBranchesService.save(id,payload);
    }

    @PutMapping("/editCenterBranches/{trainingCenterId}")
    public ResponseEntity<?> editCenterBranches(@RequestBody CenterBranchesPayload payload, @PathVariable("trainingCenterId") Long id){
        return centerBranchesService.edit(payload,id);
    }

    @DeleteMapping("/deleteCenterBranches/{centerBranchesId}")
    public ResponseEntity<?> deleteCenterBranches(@PathVariable("centerBranchesId") Long id){
        return centerBranchesService.delete(id);
    }

    @GetMapping("/getOneCenterBranches/{CenterBranchesId}")
    public ResponseEntity<?> getOneCenterBranches(@PathVariable("CenterBranchesId") Long id){
        return centerBranchesService.getOne(id);
    }

    @GetMapping("/getAllTrainingCenterId/CenterBranches/{trainingCenterId}")
    public ResponseEntity<?> getAllCenterBranches(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int size,@PathVariable("trainingCenterId") Long id){
        return centerBranchesService.getCenterBranchesTrainingId(page,size,id);
    }

    @GetMapping("/trainingCenter/token")
    public ResponseEntity<?> getTrainingCenterToken(){
        return trainingCenterService.getTrainingCenterToken();
    }

    @PostMapping("/save/userMenegerAdmin")
    public ResponseEntity<?> saveMenegerAdmin(@RequestBody UserPayload payload){
        return userService.saveMenegerAdmin(payload);
    }

    @PutMapping("/edit/userMenegerAdmin")
    public ResponseEntity<?> editMenegerAdmin(@RequestBody UserPayload payload){
        return userService.editMenegerAdmin(payload);
    }

    @GetMapping("/all/role")
    public ResponseEntity<?> getAllRoles(){
        return userService.getAllRole();
    }

    @PostMapping("/save/meneger")
    public ResponseEntity<?> saveMeneger(@RequestBody EmployePayload payload){
        return employeeService.saveMenegerAdmin(payload);
    }
}

