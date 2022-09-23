package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Block;
import uz.zako.oquv_markaz.entity.Discount;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.DiscountPayload;
import uz.zako.oquv_markaz.repository.*;
import uz.zako.oquv_markaz.service.BlockService;
import uz.zako.oquv_markaz.service.DiscountService;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlockServiceImpl implements BlockService {

    private final BlockRepository blockRepository;
    private final TrainingCenterRepository trainingCenterRepository;

    @Override
    public ResponseEntity<?> saveBlock(Long trainingCenterId){
        try {
            Block block=new Block();
            block.setTrainingCenter(trainingCenterRepository.findById(trainingCenterId).orElseThrow(()->new ResourceNotFoundException("Training Center not found")));
            block=blockRepository.save(block);
            if (block != null){
                return ResponseEntity.ok(block);
            }
            return new ResponseEntity(Result.error("save error block"),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error(" error block service",e.getMessage());
            return new ResponseEntity(Result.error("save error block"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteBlock(Long blockId){
        try {
            blockRepository.deleteById(blockId);
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error(" error block service",e.getMessage());
            return new ResponseEntity(Result.error("deleteBlock error block"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllBlock(){
        try {
            List<Block> blocks=blockRepository.findAll();
            if (blocks!=null){
                return ResponseEntity.ok(Result.ok(blocks));
            }
            return new ResponseEntity(Result.error("getAllBlock error block"),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error(" error block service",e.getMessage());
            return new ResponseEntity(Result.error("getAllBlock error block"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
