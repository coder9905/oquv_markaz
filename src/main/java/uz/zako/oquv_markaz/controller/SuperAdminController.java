package uz.zako.oquv_markaz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import uz.zako.oquv_markaz.payload.CenterBranchesPayload;
import uz.zako.oquv_markaz.payload.TrainingCenterPayload;
import uz.zako.oquv_markaz.payload.UserPayload;
import uz.zako.oquv_markaz.service.CenterBranchesService;
import uz.zako.oquv_markaz.service.TrainingCenterService;
import uz.zako.oquv_markaz.service.UserService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/superAdmin/v1")
public class SuperAdminController {

    private final TrainingCenterService trainingCenterService;
    private final CenterBranchesService centerBranchesService;
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/saveTrainingCenter")
    public ResponseEntity<?> saveTrainingCenter(@RequestBody TrainingCenterPayload payload){
        return trainingCenterService.save(payload);
    }

    @PutMapping("/editTrainingCenter/{id}")
    public ResponseEntity<?> editTrainingCenter(@PathVariable Long id, @RequestBody TrainingCenterPayload payload){
        return trainingCenterService.edit(payload,id);
    }

    @DeleteMapping("/deleteTrainingCenter/{id}")
    public ResponseEntity<?> deleteTrainingCenter(@PathVariable Long id){
        return trainingCenterService.delete(id);
    }

    @GetMapping("/allTrainingCenter")
    public ResponseEntity<?> getAllTrainingCenter(){
        return trainingCenterService.getUserTokenTrainingCenter();
    }

    @GetMapping("/getOneTrainingCenter/{trainingCenterId}")
    public ResponseEntity<?> getOneTrainingCenter(@PathVariable("trainingCenterId") Long id){
        return trainingCenterService.getOne(id);
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

    @GetMapping("/allCenterBranches")
    public ResponseEntity<?> getAllCenterBranches(){
        return centerBranchesService.getAll();
    }

    @PostMapping("/save/block/{trainingCenterId}")
    public ResponseEntity<?> saveBlock(@PathVariable("trainingCenterId") Long id){
        return trainingCenterService.savetrainingCenterBlock(id);
    }

    @DeleteMapping("/delete/block/{trainingCenterId}")
    public ResponseEntity<?> deleteBlock(@PathVariable("trainingCenterId") Long id){
        return trainingCenterService.deletetrainingCenterBlock(id);
    }

    @PostMapping("/saveTrainingCenterId/creator/{hashId}")
    public ResponseEntity<?> saveUserCenterBranchesId(@PathVariable("hashId") String id,@RequestBody UserPayload payload){
        return userService.saveCreator(id, payload);
    }

    @GetMapping("/getAllTrainingCenter")
    public ResponseEntity<?> getAllTrainingCenter(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int size) {
        return trainingCenterService.getAllPage(page, size);
    }

    @GetMapping("/getAllCenterBranches")
    public ResponseEntity<?> getAllCenterBranches(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int size) {
        return centerBranchesService.getAllPageCenterBranches(page, size);
    }

    @GetMapping("/allPage/users")
    public ResponseEntity<?> getAllPageUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size){
        return userService.getAllPageUsers(page, size);
    }

    @GetMapping("/all/users")
    public ResponseEntity<?> getAllUsers(){
        return userService.getAllUsers();
    }




}

