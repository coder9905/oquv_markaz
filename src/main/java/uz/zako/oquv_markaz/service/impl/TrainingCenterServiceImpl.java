package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.*;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.NewsPayload;
import uz.zako.oquv_markaz.payload.TrainingCenterPayload;
import uz.zako.oquv_markaz.repository.*;
import uz.zako.oquv_markaz.security.SecurityUtils;
import uz.zako.oquv_markaz.service.NewsService;
import uz.zako.oquv_markaz.service.TrainingCenterService;
import uz.zako.oquv_markaz.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrainingCenterServiceImpl implements TrainingCenterService {

    private final TrainingCenterRepository trainingCenterRepository;
    private final CenterBranchesRepository centerBranchesRepository;
    private final SecurityUtils securityUtils;
    private final PhoneRepository phoneRepository;
    private final UserRepository userRepository;


    @Override
    public ResponseEntity<?> save(TrainingCenterPayload payload){
        try {
            if (payload != null){

                TrainingCenter trainingCenter=new TrainingCenter();

                List<Phone> phones=new ArrayList<>();

                for (int i = 0; i < payload.getPhone().size(); i++) {
                    Phone phone=new Phone();
                    phone.setPhone(payload.getPhone().get(i));
                    phone=phoneRepository.save(phone);
                    phones.add(phone);
                }

                trainingCenter.setWorkingTime(payload.getWorkingTime());
                trainingCenter.setBlock(payload.isBlock());
                trainingCenter.setName(payload.getName());
                trainingCenter.setPhones(phones);
                trainingCenter=trainingCenterRepository.save(trainingCenter);

                CenterBranches centerBranches=new CenterBranches();

                centerBranches.setName(payload.getName());
                centerBranches.setPhones(phones);
                centerBranches.setWorkingTime(payload.getWorkingTime());



                centerBranches.setTraining_center(trainingCenterRepository.findById(trainingCenter.getId()).orElseThrow(()->new ResourceNotFoundException("TrainingCenter not found")));

                centerBranches=centerBranchesRepository.save(centerBranches);

                return ResponseEntity.ok(Result.ok(trainingCenter));

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
            trainingCenterRepository.deleteById(id);
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error("error TrainingCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> edit(TrainingCenterPayload payload, Long id){
        try {
            TrainingCenter trainingCenter=trainingCenterRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TrainingCenter not found"));

            trainingCenter.setName(payload.getName());
            List<Phone> phones1=new ArrayList<>();

            if (payload.getPhone() != null){
                for (int i = 0; i < payload.getPhone().size(); i++) {
                    Phone phone=new Phone();
                    phone.setPhone(payload.getPhone().get(i));
                    phone=phoneRepository.save(phone);
                    phones1.add(phone);
                }
            }
            trainingCenter.setPhones(phones1);
            trainingCenter.setWorkingTime(payload.getWorkingTime());
            trainingCenter=trainingCenterRepository.save(trainingCenter);

            return ResponseEntity.ok(trainingCenter);

        }catch (Exception e){
            log.error("error TrainingCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long id){
        try {
            TrainingCenter trainingCenter=trainingCenterRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("trainingCenter not found"));
            return ResponseEntity.ok(trainingCenter);
        }catch (Exception e){
            log.error("error TrainingCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAll(){
        try {
            List<TrainingCenter> trainingCenters=trainingCenterRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
            if (trainingCenters != null) {
                return ResponseEntity.ok(trainingCenters);
            }
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error TrainingCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllPage(int page, int size){
        try {
            Page<TrainingCenter> trainingCenters=trainingCenterRepository.findAll(PageRequest.of(page,size,Sort.by(Sort.Direction.DESC, "createdAt")));
            if (trainingCenters != null) {
                return ResponseEntity.ok(trainingCenters);
            }
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error TrainingCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getUserTokenTrainingCenter(){
        try {
            String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
            User user=userRepository.findByUsername(username);
            if (user.getUsername().equals("coder") || user.getUsername().equals("joha")){
                List<TrainingCenter> centerPayload=trainingCenterRepository.findAll();
                return ResponseEntity.ok(Result.ok(centerPayload));
            }else {
                List<TrainingCenter> centerPayload = trainingCenterRepository.getTrainingCenterUserToken(user.getUsername());
                if (centerPayload != null) {
                    return ResponseEntity.ok(Result.ok(centerPayload));
                }
            }
            return new ResponseEntity(Result.error("Bizda bunday markazlar mavjud emas"),HttpStatus.BAD_REQUEST);

        }catch (Exception e){
            log.error("error TrainingCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> savetrainingCenterBlock(Long trainingCenterId){
        try {
            TrainingCenter trainingCenter=trainingCenterRepository.findById(trainingCenterId).orElseThrow(()->new ResourceNotFoundException("TrainingCenter not found"));

            trainingCenter.setBlock(true);

            trainingCenterRepository.save(trainingCenter);
            if (trainingCenter!=null) {
                return ResponseEntity.ok("Block succesfull");
            }
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error TrainingCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deletetrainingCenterBlock(Long trainingCenterId){
        try {
            TrainingCenter trainingCenter=trainingCenterRepository.findById(trainingCenterId).orElseThrow(()->new ResourceNotFoundException("TrainingCenter not found"));

            trainingCenter.setBlock(false);

            trainingCenter=trainingCenterRepository.save(trainingCenter);

            if (trainingCenter!=null) {
                return ResponseEntity.ok("delete Block succesfull");
            }
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error TrainingCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getTrainingCenterToken(){
        try {
            String username=securityUtils.getCurrentUser().orElseThrow(()->new RuntimeException("error"));
            List<TrainingCenter> trainingCenters=trainingCenterRepository.getTrainingCenterUserToken(username);
            if (trainingCenters != null){
                return ResponseEntity.ok(Result.ok(trainingCenters));
            }
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error TrainingCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    public ResponseEntity<?> getAll(int page, int size, LanguageEnum languageEnum) {
//        return categoryTranslateRepository.getAllWithLang(PageRequest.of(page,size), languageEnum);
//    }



}
