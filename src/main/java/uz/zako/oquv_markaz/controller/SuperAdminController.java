package uz.zako.oquv_markaz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.oquv_markaz.payload.CenterBranchesPayload;
import uz.zako.oquv_markaz.payload.TrainingCenterPayload;
import uz.zako.oquv_markaz.service.BlockService;
import uz.zako.oquv_markaz.service.CenterBranchesService;
import uz.zako.oquv_markaz.service.TrainingCenterService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/superAdmin/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class SuperAdminController {

    private final TrainingCenterService trainingCenterService;
    private final CenterBranchesService centerBranchesService;
    private final BlockService blockService;

    @GetMapping("/getOneTrainingCenter/{trainingCenterId}")
    public ResponseEntity<?> getOneTrainingCenter(@PathVariable("trainingCenterId") Long id){
        return trainingCenterService.getOne(id);
    }

    @GetMapping("/allTrainingCenter")
    public ResponseEntity<?> getAllTrainingCenter(){
        return trainingCenterService.getAll();
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

    @GetMapping("/getOneCenterBranches/{CenterBranchesId}")
    public ResponseEntity<?> getOneCenterBranches(@PathVariable("CenterBranchesId") Long id){
        return centerBranchesService.getOne(id);
    }

    @GetMapping("/allCenterBranches")
    public ResponseEntity<?> getAllCenterBranches(){
        return centerBranchesService.getAll();
    }

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

    @PostMapping("/save/block/{trainingCenterId}")
    public ResponseEntity<?> saveBlock(@PathVariable("trainingCenterId") Long id){
        return blockService.saveBlock(id);
    }

    @DeleteMapping("/delete/block/{blockId}")
    public ResponseEntity<?> deleteBlock(@PathVariable("blockId") Long id){
        return blockService.deleteBlock(id);
    }

    @GetMapping("/all/block")
    public ResponseEntity<?> getAllBlock(){
        return blockService.getAllBlock();
    }

}
