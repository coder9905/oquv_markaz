package uz.zako.oquv_markaz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.oquv_markaz.service.CenterBranchesService;
import uz.zako.oquv_markaz.service.TrainingCenterService;
import uz.zako.oquv_markaz.service.UserService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/token/v1")
public class TokenController {

    private final TrainingCenterService trainingCenterService;
    private final CenterBranchesService centerBranchesService;
    private final UserService userService;

    @GetMapping("/get/trainingCenterUserToken")
    public ResponseEntity<?> getTrainingCenterUserToken(){
        return trainingCenterService.getUserTokenTrainingCenter();
    }


}

