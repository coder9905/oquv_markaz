package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Discount;
import uz.zako.oquv_markaz.entity.Outputs;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.DiscountPayload;
import uz.zako.oquv_markaz.payload.OutputPayload;
import uz.zako.oquv_markaz.repository.DiscountRepository;
import uz.zako.oquv_markaz.repository.GroupRepository;
import uz.zako.oquv_markaz.repository.OutputRepository;
import uz.zako.oquv_markaz.repository.UserRepository;
import uz.zako.oquv_markaz.security.SecurityUtils;
import uz.zako.oquv_markaz.service.DiscountService;
import uz.zako.oquv_markaz.service.OutputService;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OutputServiceImpl implements OutputService {

    private final OutputRepository outputRepository;
    private final UserRepository userRepository;
    private final SecurityUtils securityUtils;

    @Override
    public ResponseEntity<?> saveOutput(OutputPayload payload){
        try {
            Outputs outputs=new Outputs();
            outputs.setTitle(payload.getTitle());
            outputs.setDiscription(payload.getDiscription());
            outputs.setPrice(payload.getPrice());

            String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
            outputs.setUser(userRepository.findByUsername(username));

            outputs=outputRepository.save(outputs);

            if (outputs != null){
                return ResponseEntity.ok(Result.ok(outputs));
            }
            return new ResponseEntity(new Result(false,"error save outputs",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error outputs(chiqim)",e.getMessage());
            return new ResponseEntity(new Result(false,"error save outputs",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> editDiscount(Long outputId,OutputPayload payload){
        try {
            Outputs outputs=outputRepository.findById(outputId).orElseThrow(()->new ResourceNotFoundException("outputs not founds"));
            String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
            if (outputs.getUser().getUsername().equals(username)){
                outputs.setTitle(payload.getTitle());
                outputs.setDiscription(payload.getDiscription());
                outputs.setPrice(payload.getPrice());
                outputs.setUser(userRepository.findByUsername(username));
                outputs=outputRepository.save(outputs);
                if (outputs != null){
                    return ResponseEntity.ok(Result.ok(outputs));
                }
            }
            return new ResponseEntity(new Result(false,"error edit outputs",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error outputs(chiqim)",e.getMessage());
            return new ResponseEntity(new Result(false,"error edit outputs",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<?> getAllOutput(){
        try {
            List<Outputs> outputss=outputRepository.findAll();
            return ResponseEntity.ok(Result.ok(outputss));
        }catch (Exception e){
            log.error("error outputs",e.getMessage());
            return new ResponseEntity(new Result(false,"error getAll outputs",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getOutputGroupId(Long userId){
        try {
            List<Outputs> outputss=outputRepository.getOutputUserId(userId);
            return ResponseEntity.ok(Result.ok(outputss));
        }catch (Exception e){
            log.error("error outputs(chqim)",e.getMessage());
            return new ResponseEntity(new Result(false,"error getAll outputs",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteOutputuserId(Long userId){
        try {
            outputRepository.deleteOutputUserId(userId);
            return ResponseEntity.ok(Result.ok("delete succesfull"));
        }catch (Exception e){
            log.error("error outputs(chiqim)",e.getMessage());
            return new ResponseEntity(new Result(false,"error getAll outputs",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}