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
import uz.zako.oquv_markaz.service.UserService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/token/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class TokenController {

    private final TrainingCenterService trainingCenterService;
    private final CenterBranchesService centerBranchesService;
    private final BlockService blockService;
    private final UserService userService;

    @GetMapping("/get/trainingCenterUserToken")
    public ResponseEntity<?> getTrainingCenterUserToken(){
        return trainingCenterService.getUserTokenTrainingCenter();
    }

    @GetMapping("/get/centerBranchesUserToken/{trainingCenterId}")
    public ResponseEntity<?> getCenterBranchesUserToken(@PathVariable("trainingCenterId") Long id){
        return centerBranchesService.getCenterBranchesTokenId(id);
    }

}

