package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Category;
import uz.zako.oquv_markaz.entity.News;
import uz.zako.oquv_markaz.entity.TrainingCenter;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.NewsPayload;
import uz.zako.oquv_markaz.payload.TrainingCenterPayload;
import uz.zako.oquv_markaz.repository.AttachmentRepository;
import uz.zako.oquv_markaz.repository.CategoryRepository;
import uz.zako.oquv_markaz.repository.NewsRepository;
import uz.zako.oquv_markaz.repository.TrainingCenterRepository;
import uz.zako.oquv_markaz.service.NewsService;
import uz.zako.oquv_markaz.service.TrainingCenterService;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrainingCenterServiceImpl implements TrainingCenterService {

    private final TrainingCenterRepository trainingCenterRepository;

    @Override
    public ResponseEntity<?> save(TrainingCenterPayload payload){
        try {
            if (payload != null){
                TrainingCenter trainingCenter=new TrainingCenter();
                trainingCenter.setName(payload.getName());
                trainingCenter.setPhone(payload.getPhone());
                trainingCenterRepository.save(trainingCenter);
                return ResponseEntity.ok("save succesfull");
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
            trainingCenter.setPhone(payload.getPhone());

            trainingCenterRepository.save(trainingCenter);

            return ResponseEntity.ok("edit succesfull");

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
            List<TrainingCenter> trainingCenters=trainingCenterRepository.findAll();
            if (trainingCenters != null) {
                return ResponseEntity.ok(trainingCenters);
            }
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error TrainingCenter",e.getMessage());
            return new ResponseEntity(new Result(false,"error TrainingCenter",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
