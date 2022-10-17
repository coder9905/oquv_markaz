package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Discount;
import uz.zako.oquv_markaz.entity.Week;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.DiscountPayload;
import uz.zako.oquv_markaz.payload.WeekPayload;
import uz.zako.oquv_markaz.repository.DiscountRepository;
import uz.zako.oquv_markaz.repository.GroupRepository;
import uz.zako.oquv_markaz.repository.WeekRepository;
import uz.zako.oquv_markaz.service.DiscountService;
import uz.zako.oquv_markaz.service.WeekService;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final GroupRepository groupRepository;

    @Override
    public ResponseEntity<?> saveDiscount(Long groupId, DiscountPayload payload){
        try {
            Discount discount=new Discount();
            discount.setTime(payload.getTime());
            discount.setFoiz(payload.getFoizi());
            discount=discountRepository.save(discount);
            if (discount != null){
                return ResponseEntity.ok(Result.ok(discount));
            }
            return new ResponseEntity(new Result(false,"error save discount",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error discount(chegirma)",e.getMessage());
            return new ResponseEntity(new Result(false,"error save discount",null),HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<?> editDiscount(Long groupId, DiscountPayload payload){
        try {
            Discount discount=discountRepository.findById(payload.getId()).orElseThrow(()->new ResourceNotFoundException("discount not found"));
            discount.setTime(payload.getTime());
            discount.setFoiz(payload.getFoizi());
            discount.setGroups(Arrays.asList(groupRepository.findById(groupId).orElseThrow(()->new ResourceNotFoundException("discount not found"))));
            discount=discountRepository.save(discount);
            if (discount != null){
                return ResponseEntity.ok(Result.ok(discount));
            }
            return new ResponseEntity(new Result(false,"error edit discount",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error discount(chegirma)",e.getMessage());
            return new ResponseEntity(new Result(false,"error edit discount",null),HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<?> getDiscountId(Long discountId){
        try {
            Discount discount=discountRepository.findById(discountId).orElseThrow(()->new ResourceNotFoundException("discount not found"));
            return ResponseEntity.ok(Result.ok(discount));
        }catch (Exception e){
            log.error("error discount(chegirma)",e.getMessage());
            return new ResponseEntity(new Result(false,"error getDiscountId discount",null),HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<?> getAllDiscount(){
        try {
            List<Discount> discounts=discountRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
            return ResponseEntity.ok(Result.ok(discounts));
        }catch (Exception e){
            log.error("error discount(chegirma)",e.getMessage());
            return new ResponseEntity(new Result(false,"error getAll discount",null),HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<?> getDiscountGroupId(Long groupId){
        try {
            List<Discount> discounts=discountRepository.getDiscountGroupId(groupId);
            return ResponseEntity.ok(Result.ok(discounts));
        }catch (Exception e){
            log.error("error discount(chegirma)",e.getMessage());
            return new ResponseEntity(new Result(false,"error getAll discount",null),HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<?> deleteDiscountGroupId(Long groupId){
        try {
            discountRepository.deleteDiscountGroupId(groupId);
            return ResponseEntity.ok(Result.ok("delete succesfull"));
        }catch (Exception e){
            log.error("error discount(chegirma)",e.getMessage());
            return new ResponseEntity(new Result(false,"error getAll discount",null),HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<?> deleteDiscountId(Long discountId){
        try {
            discountRepository.deleteById(discountId);
            return ResponseEntity.ok(Result.ok("delete succesfull"));
        }catch (Exception e){
            log.error("error discount(chegirma)",e.getMessage());
            return new ResponseEntity(new Result(false,"error getAll discount",null),HttpStatus.CONFLICT);
        }
    }







}
