package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.CenterBranches;
import uz.zako.oquv_markaz.entity.Phone;
import uz.zako.oquv_markaz.entity.TrainingCenter;
import uz.zako.oquv_markaz.entity.User;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.CenterBranchesPayload;
import uz.zako.oquv_markaz.payload.TrainingCenterPayload;
import uz.zako.oquv_markaz.repository.CenterBranchesRepository;
import uz.zako.oquv_markaz.repository.PhoneRepository;
import uz.zako.oquv_markaz.repository.TrainingCenterRepository;
import uz.zako.oquv_markaz.repository.UserRepository;
import uz.zako.oquv_markaz.security.SecurityUtils;
import uz.zako.oquv_markaz.service.CenterBranchesService;
import uz.zako.oquv_markaz.service.TrainingCenterService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CenterBranchesServiceImpl implements CenterBranchesService {

    private final CenterBranchesRepository centerBranchesRepository;
    private final TrainingCenterRepository trainingCenterRepository;
    private final SecurityUtils securityUtils;
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;

    @Override
    public ResponseEntity<?> save(Long trainingCenterId, CenterBranchesPayload payload){
        try {
            if (payload != null){
                CenterBranches centerBranches=new CenterBranches();

                centerBranches.setName(payload.getName());
                List<Phone> phones=new ArrayList<>();
                for (int i = 0; i < payload.getPhone().size(); i++) {
                    Phone phone=new Phone();
                    phone.setPhone(payload.getPhone().get(i));
                    phone=phoneRepository.save(phone);
                    phones.add(phone);
                }
                centerBranches.setPhones(phones);
                centerBranches.setWorkingTime(payload.getWorkingTime());

                centerBranches.setTraining_center(trainingCenterRepository.findById(trainingCenterId).orElseThrow(()->new ResourceNotFoundException("training center topilmadi")));

                centerBranchesRepository.save(centerBranches);

                return ResponseEntity.ok(centerBranches);
            }
            return new ResponseEntity(new Result(false,"error BranchesCenter",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error BranchesCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error BranchesCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
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
            List<Phone> phones1=centerBranches.getPhones();
            for (int i = 0; i < payload.getPhone().size(); i++) {
                Phone phone=new Phone();
                phone.setPhone(payload.getPhone().get(i));
                phone=phoneRepository.save(phone);
                phones1.add(phone);
            }
            centerBranches.setPhones(phones1);
            centerBranches.setWorkingTime(payload.getWorkingTime());
            centerBranches.setTraining_center(trainingCenterRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TrainingCenter not found")));

            centerBranchesRepository.save(centerBranches);

            return ResponseEntity.ok("edit succesfull");

        }catch (Exception e){
            log.error("error BranchesCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error BranchesCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long id){
     try {
         CenterBranches centerBranches=centerBranchesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("CenterBranches not found"));
         return ResponseEntity.ok(centerBranches);
     }catch (Exception e){
         log.error("error BranchesCenter",e.getMessage());
         return new ResponseEntity(new Result(false,"error BranchesCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
     }
    }

    @Override
    public ResponseEntity<?> getAll(){
     try {
         List<CenterBranches> centerBranches=centerBranchesRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
         if (centerBranches != null) {
             return ResponseEntity.ok(centerBranches);
         }
         return new ResponseEntity(new Result(false,"error BranchesCenter",null),HttpStatus.BAD_REQUEST);
     }catch (Exception e){
         log.error("error BranchesCenter",e.getMessage());
         return new ResponseEntity(new Result(false,"error BranchesCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
     }
    }

    @Override
    public ResponseEntity<?> getCenterBranchesTrainingId(int page, int size,Long id){
        try {
            Page<CenterBranches> payloads=centerBranchesRepository.getTrainingCenterId(PageRequest.of(page,size),id);
            if (payloads != null){
                return ResponseEntity.ok(payloads);
            }
            return new ResponseEntity(new Result(false,"error BranchesCenter",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error BranchesCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error BranchesCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getCenterBranchesTokenId(){
        try {
            String username=securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
            User user=userRepository.findByUsername(username);

            CenterBranches centerBranches=centerBranchesRepository.getCenterBranchesUserToken(user.getUsername());

            if (centerBranches != null){
                return ResponseEntity.ok(Result.ok(centerBranches));
            }
            return new ResponseEntity(new Result(false,"error BranchesCenter",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error BranchesCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error BranchesCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllPageCenterBranches(int page, int size){
        try {
            Page<CenterBranches> centerBranches=centerBranchesRepository.findAllByDesc(PageRequest.of(page,size));
            if (centerBranches != null) {
                return ResponseEntity.ok(centerBranches);
            }
            return new ResponseEntity(new Result(false,"error CenterBranches",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error CenterBranches",e.getMessage());
            return new ResponseEntity(new Result(false,"error CenterBranches",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllCenterBranchesTrainingCenterId(Long id){
        try {
            List<CenterBranches> centerBranches=centerBranchesRepository.getCenterBranchesTrainingCenterId(id);
            if (centerBranches != null){
                return ResponseEntity.ok(Result.ok(centerBranches));
            }
            return new ResponseEntity(new Result(false,"error CenterBranches",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error CenterBranches",e.getMessage());
            return new ResponseEntity(new Result(false,"error CenterBranches",null),HttpStatus.BAD_REQUEST);
        }
    }

}
