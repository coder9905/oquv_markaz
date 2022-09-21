package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.CenterBranches;
import uz.zako.oquv_markaz.entity.TrainingCenter;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.CenterBranchesPayload;
import uz.zako.oquv_markaz.payload.TrainingCenterPayload;
import uz.zako.oquv_markaz.repository.CenterBranchesRepository;
import uz.zako.oquv_markaz.repository.TrainingCenterRepository;
import uz.zako.oquv_markaz.service.CenterBranchesService;
import uz.zako.oquv_markaz.service.TrainingCenterService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CenterBranchesServiceImpl implements CenterBranchesService {

    private final CenterBranchesRepository centerBranchesRepository;
    private final TrainingCenterRepository trainingCenterRepository;

    @Override
    public ResponseEntity<?> save(Long trainingCenterId, CenterBranchesPayload payload){
        try {
            if (payload != null){
                CenterBranches centerBranches=new CenterBranches();

                centerBranches.setName(payload.getName());
                centerBranches.setPhone(payload.getPhone());
                centerBranches.setWorkingTime(payload.getWorkingTime());

                centerBranches.setTraining_center(trainingCenterRepository.findById(trainingCenterId).orElseThrow(()->new ResourceNotFoundException("training center topilmadi")));

                centerBranchesRepository.save(centerBranches);

                return ResponseEntity.ok(centerBranches);
            }
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error TrainingCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id){
        try {
            centerBranchesRepository.deleteById(id);
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error("error Center Branches",e.getMessage());
            return new ResponseEntity(new Result(false,"error Center Branches",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> edit(CenterBranchesPayload payload, Long id){
        try {

            CenterBranches centerBranches=centerBranchesRepository.findById(payload.getId()).orElseThrow(()->new ResourceNotFoundException("centerBranches not found"));

            centerBranches.setName(payload.getName());
            centerBranches.setPhone(payload.getPhone());
            centerBranches.setWorkingTime(payload.getWorkingTime());
            centerBranches.setTraining_center(trainingCenterRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TrainingCenter not found")));

            centerBranchesRepository.save(centerBranches);

            return ResponseEntity.ok("edit succesfull");

        }catch (Exception e){
            log.error("error TrainingCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long id){
     try {
         CenterBranches centerBranches=centerBranchesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("CenterBranches not found"));
         return ResponseEntity.ok(centerBranches);
     }catch (Exception e){
         log.error("error TrainingCenter",e.getMessage());
         return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
     }
    }

    @Override
    public ResponseEntity<?> getAll(){
     try {
         List<CenterBranches> centerBranches=centerBranchesRepository.findAll();
         if (centerBranches != null) {
             return ResponseEntity.ok(centerBranches);
         }
         return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.BAD_REQUEST);
     }catch (Exception e){
         log.error("error TrainingCenter",e.getMessage());
         return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
     }
    }

    @Override
    public ResponseEntity<?> getTrainingId(Long id){
        try {
            List<CenterBranchesPayload> payloads=centerBranchesRepository.getTrainingCenterId(id);
            if (payloads != null){
                return ResponseEntity.ok(payloads);
            }
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error TrainingCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
