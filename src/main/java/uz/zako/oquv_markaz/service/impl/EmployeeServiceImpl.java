package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.CenterBranches;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.entity.Phone;
import uz.zako.oquv_markaz.entity.User;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.EmployePayload;
import uz.zako.oquv_markaz.payload.UserPayload;
import uz.zako.oquv_markaz.repository.*;
import uz.zako.oquv_markaz.security.SecurityUtils;
import uz.zako.oquv_markaz.service.EmployeeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployesRepository employesRepository;
    private final CenterBranchesRepository centerBranchesRepository;
    private final AttachmentRepository attachmentRepository;
    private final RoleRepository roleRepository;
    private final SecurityUtils securityUtils;
    private final PhoneRepository phoneRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?>  save(EmployePayload payload, String hashId){
        try {
            Employee employee=new Employee();
            employee.setFullName(payload.getFullName());
            List<Phone> phones=new ArrayList<>();
            for (int i = 0; i < payload.getPhones().size(); i++) {
                Phone phone = new Phone();
                phone.setPhone(payload.getPhones().get(i));
                phone = phoneRepository.save(phone);
                phones.add(phone);
            }
            employee.setAdress(payload.getAdress());
            employee.setUsername(payload.getUsername());
            employee.setPassword(passwordEncoder.encode(payload.getPassword()));
            employee.setSeniority(payload.getSeniority());
            employee.setTeacher(payload.isTeacher());
            employee.setSeniority(payload.getSeniority());
            employee.setPhones(phones);
            employee.setMonthly(payload.getMonthly());
            employee.setRoles(Arrays.asList(roleRepository.findByName(payload.getRole())));
            if (hashId!=null) {
                employee.setImg(attachmentRepository.findByHashId1(hashId).orElseThrow(() -> new ResourceNotFoundException("Attachment not found")));
            }
            employee.setCenterBranches(centerBranchesRepository.findById(payload.getCenterBranchesId()).orElseThrow(()->new ResourceNotFoundException("CenterBranches not found")));
            employee=employesRepository.save(employee);
            if (employee != null) {
                return ResponseEntity.ok("save succesfull");
            }
            return new ResponseEntity(new Result(false,"error save employes",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error employes",e.getMessage());
            return new ResponseEntity(new Result(false,"employe saqlashda hatolik",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> saveMenegerAdmin(String hashId, EmployePayload payload){
        try {
            Employee employee=new Employee();
            employee.setFullName(payload.getFullName());
            employee.setAdress(payload.getAdress());
            employee.setSeniority(payload.getSeniority());
            employee.setTeacher(payload.isTeacher());
            employee.setSeniority(payload.getSeniority());
            employee.setRoles(Arrays.asList(roleRepository.findByName(payload.getRole())));
            employee.setImg(attachmentRepository.findByHashId1(hashId).orElseThrow(()->new ResourceNotFoundException("Attachment not found")));
            employee.setCenterBranches(centerBranchesRepository.findById(payload.getCenterBranchesId()).orElseThrow(()->new ResourceNotFoundException("CenterBranches not found")));

            List<Phone> phones=new ArrayList<>();
            for (int i = 0; i < payload.getPhones().size(); i++) {
                Phone phone = new Phone();
                phone.setPhone(payload.getPhones().get(i));
                phone = phoneRepository.save(phone);
                phones.add(phone);
            }
            employee.setPhones(phones);
            employee=employesRepository.save(employee);
            if (employee != null){
                return ResponseEntity.ok(Result.ok(employee));
            }
            return new ResponseEntity(Result.error("error saveMenegerAdmin not succesfull"), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e) {
            log.error("saveMenegerAdmin error", e.getMessage());
            return new ResponseEntity(Result.error("error saveMenegerAdmin not succesfull"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long id){
        try {
            Employee employee=employesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("employe not found"));
            return ResponseEntity.ok(new Result(true,"getOneEmploye",employee));
        }catch (Exception e){
            log.error("error employes",e.getMessage());
            return new ResponseEntity(new Result(false,"get One employe error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getEmployeCenterBranchesId(int size, int page,Long id){
        try {
            Page<Employee> employePayloads=employesRepository.getAllEmployeCenterBranchesId(PageRequest.of(page, size),id);
            if (employePayloads != null){
                return ResponseEntity.ok(new Result(true,"get Employe centerBranchesId",employePayloads));
            }
            return new ResponseEntity(new Result(false,"getEmployeCenterBranchesId employe error",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error employes",e.getMessage());
            return new ResponseEntity(new Result(false,"getEmployeCenterBranchesId employe error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllEmployee(){
        try {
            List<Employee> employees=employesRepository.findAll();
            if (employees != null){
                return ResponseEntity.ok(new Result(true,"getAll Employe",employees));
            }
            return new ResponseEntity(new Result(false,"getAll employe error",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error employes",e.getMessage());
            return new ResponseEntity(new Result(false,"getAll employe error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> editEmploye(EmployePayload payload,String hashId){
        try {
            Employee employee=employesRepository.findById(payload.getId()).orElseThrow(()->new ResourceNotFoundException("employe not found"));
            employee.setFullName(payload.getFullName());
            List<Phone> phones=new ArrayList<>();
            for (int i = 0; i < payload.getPhones().size(); i++) {
                Phone phone = new Phone();
                phone.setPhone(payload.getPhones().get(i));
                phone = phoneRepository.save(phone);
                phones.add(phone);
            }
            employee.setAdress(payload.getAdress());
            employee.setUsername(payload.getUsername());
            employee.setPassword(passwordEncoder.encode(payload.getPassword()));
            employee.setSeniority(payload.getSeniority());
            employee.setTeacher(payload.isTeacher());
            employee.setSeniority(payload.getSeniority());
            employee.setImg(attachmentRepository.findByHashId1(hashId).orElseThrow(()->new ResourceNotFoundException("Attachment not found")));
            employee.setCenterBranches(centerBranchesRepository.findById(payload.getId()).orElseThrow(()->new ResourceNotFoundException("CenterBranches not found")));
            employee=employesRepository.save(employee);
            if (employee != null){
                return ResponseEntity.ok(new Result(true,"edit Employe",employee));
            }
            return new ResponseEntity(new Result(false,"edit employe error",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error employes",e.getMessage());
            return new ResponseEntity(new Result(false,"edit employe error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteEmploye(Long employeId){
        try {
            Employee employee=employesRepository.findById(employeId).orElseThrow(()->new ResourceNotFoundException("employee not found"));
            employesRepository.deleteById(employeId);
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error("error employes",e.getMessage());
            return new ResponseEntity(new Result(false,"delete employe error",null), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> deleteEmployeCenterBranchesId(Long centerBranchesId){
        try {
            if (employesRepository.deleteEmployeeByCenterBranchesId(centerBranchesId)) {
                return ResponseEntity.ok("delete succesfull");
            }
            return new ResponseEntity(new Result(false,"delete employe error",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error employes",e.getMessage());
            return new ResponseEntity(new Result(false,"delete employe error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
