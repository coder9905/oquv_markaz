package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Payment;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.PaymentPayload;
import uz.zako.oquv_markaz.repository.GroupRepository;
import uz.zako.oquv_markaz.repository.PaymentRepository;
import uz.zako.oquv_markaz.repository.UserRepository;
import uz.zako.oquv_markaz.security.SecurityUtils;
import uz.zako.oquv_markaz.service.PaymentService;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final SecurityUtils securityUtils;
    String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));

    @Override
    public ResponseEntity<?> savePayment(PaymentPayload payload){
        try {
            Payment payment=new Payment();
            payment.setMoon(payment.getMoon());
            payment.setPrice(payment.getPrice());
            payment.setDiscount(payment.getDiscount());
            payment.setGroups(Arrays.asList(groupRepository.findById(payload.getGroupsId()).get()));
            String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
            payment.setUsers(Arrays.asList(userRepository.findByUsername(username)));

            payment=paymentRepository.save(payment);

            if (payment != null){
                return ResponseEntity.ok(Result.ok(payment));
            }
            return new ResponseEntity(new Result(false,"error save payment",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error payment(tolovlar)",e.getMessage());
            return new ResponseEntity(new Result(false,"error save payment",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> editPayment(Long paymentId, PaymentPayload payload){
        try {
            Payment payment=paymentRepository.findById(paymentId).orElseThrow(()->new ResourceNotFoundException("payment not found"));
            String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
            if (payment.getUsers().get(0).getUsername().equals(username)){
                payment.setMoon(payment.getMoon());
                payment.setPrice(payment.getPrice());
                payment.setDiscount(payment.getDiscount());
                payment.setGroups(Arrays.asList(groupRepository.findById(payload.getGroupsId()).get()));
                payment=paymentRepository.save(payment);
                if (payment != null){
                    return ResponseEntity.ok(Result.ok(payment));
                }
            }
            return new ResponseEntity(new Result(false,"error edit payment",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error payment(tolovlar)",e.getMessage());
            return new ResponseEntity(new Result(false,"error edit payment",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllPayment(){
        try {
            List<Payment> payments=paymentRepository.findAll();
            return ResponseEntity.ok(Result.ok(payments));
        }catch (Exception e){
            log.error("error payment",e.getMessage());
            return new ResponseEntity(new Result(false,"error getAll payment",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getPaymentUserId(Long userId){
        try {
            List<Payment> payments=paymentRepository.getPaymentUSerId(userId);
            return ResponseEntity.ok(Result.ok(payments));
        }catch (Exception e){
            log.error("error payment(tolovlar)",e.getMessage());
            return new ResponseEntity(new Result(false,"error getPaymentUserId payment",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getPaymentMoonId(Long moonId){
        try {
            List<Payment> payments=paymentRepository.getPaymenMoonId(moonId);
            return ResponseEntity.ok(Result.ok(payments));
        }catch (Exception e){
            log.error("error payment(tolovlar)",e.getMessage());
            return new ResponseEntity(new Result(false,"error getPaymentMoonId payment",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getPaymentGroupId(Long groupId){
        try {
            List<Payment> payments=paymentRepository.getPaymenGroupId(groupId);
            return ResponseEntity.ok(Result.ok(payments));
        }catch (Exception e){
            log.error("error payment(tolovlar)",e.getMessage());
            return new ResponseEntity(new Result(false,"error getPaymentGroupId payment",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deletePaymentId(Long paymentId){
        try {
            Payment payment=paymentRepository.findById(paymentId).orElseThrow(()->new ResourceNotFoundException("payment not found"));
            if (payment.getUsers().get(0).getUsername().equals(username)){
                paymentRepository.deleteById(paymentId);
                return ResponseEntity.ok(Result.ok("delete succesfull"));
            }
            return new ResponseEntity(new Result(false,"error delete payment",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error payment(tolovlar)",e.getMessage());
            return new ResponseEntity(new Result(false,"error delete payment",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deletePaymentGroupId(Long groupId){
        try {
            paymentRepository.deletePaymenGroupId(groupId);
            return ResponseEntity.ok(("delete succesfull"));
        }catch (Exception e){
            log.error("error payment(tolovlar)",e.getMessage());
            return new ResponseEntity(new Result(false,"error delete payment",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
