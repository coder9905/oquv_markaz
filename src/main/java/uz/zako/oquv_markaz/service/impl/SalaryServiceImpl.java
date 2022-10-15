package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Salary;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.SalaryPayload;
import uz.zako.oquv_markaz.repository.*;
import uz.zako.oquv_markaz.repository.SalaryRepository;
import uz.zako.oquv_markaz.security.SecurityUtils;
import uz.zako.oquv_markaz.service.SalaryService;
import uz.zako.oquv_markaz.service.SalaryService;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository salaryRepository;
    private final UserRepository userRepository;
    private final EmployesRepository employesRepository;
    private final SecurityUtils securityUtils;

    @Override
    public ResponseEntity<?> saveSalary(SalaryPayload payload){
        try {

            Salary salary=new Salary();
            salary.setTitle(payload.getTitle());
            salary.setPrice(payload.getPrice());
            salary.setMonth(payload.getMonth());
            salary.setEmployee(employesRepository.findById(payload.getEmployeeId()).orElseThrow(()->new ResourceNotFoundException("employee not found")));
            String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
            salary.setUser(userRepository.findByUsername(username));

            if (salary != null){
                return ResponseEntity.ok(Result.ok(salary));
            }
            return new ResponseEntity(new Result(false,"error save salary",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error salary(tolovlar)",e.getMessage());
            return new ResponseEntity(new Result(false,"error save salary",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> editSalary(Long salaryId, SalaryPayload payload){
        try {
            Salary salary=salaryRepository.findById(salaryId).orElseThrow(()->new ResourceNotFoundException("salary not found"));
            String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
            if (salary.getUser().getUsername().equals(username)){
                salary.setTitle(payload.getTitle());
                salary.setPrice(payload.getPrice());
                salary.setMonth(payload.getMonth());
                salary.setEmployee(employesRepository.findById(payload.getEmployeeId()).orElseThrow(()->new ResourceNotFoundException("employee not found")));
                salary.setUser(userRepository.findByUsername(username));
                salary=salaryRepository.save(salary);
                if (salary != null){
                    return ResponseEntity.ok(Result.ok(salary));
                }
            }
            return new ResponseEntity(new Result(false,"error edit salary",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error salary(tolovlar)",e.getMessage());
            return new ResponseEntity(new Result(false,"error edit salary",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllSalary(){
        try {
            List<Salary> salarys=salaryRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
            return ResponseEntity.ok(Result.ok(salarys));
        }catch (Exception e){
            log.error("error salary",e.getMessage());
            return new ResponseEntity(new Result(false,"error getAll salary",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getSalaryMoonId(Long moonId){
        try {
            List<Salary> salarys=salaryRepository.getSalaryMoonId(moonId);
            return ResponseEntity.ok(Result.ok(salarys));
        }catch (Exception e){
            log.error("error salary(tolovlar)",e.getMessage());
            return new ResponseEntity(new Result(false,"error getSalaryMoonId salary",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<?> deleteSalaryId(Long salaryId){
        try {
            Salary salary=salaryRepository.findById(salaryId).orElseThrow(()->new ResourceNotFoundException("salary not found"));
            String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
            if (salary.getUser().getUsername().equals(username)){
                salaryRepository.deleteById(salaryId);
                return ResponseEntity.ok(Result.ok("delete succesfull"));
            }
            return new ResponseEntity(new Result(false,"error delete salary",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error salary(tolovlar)",e.getMessage());
            return new ResponseEntity(new Result(false,"error delete salary",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteSalaryEmployeId(Long employeId){
        try {
            salaryRepository.deleteSalaryEmployeId(employeId);
            return ResponseEntity.ok(("delete succesfull"));
        }catch (Exception e){
            log.error("error salary(tolovlar)",e.getMessage());
            return new ResponseEntity(new Result(false,"error delete salary",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
