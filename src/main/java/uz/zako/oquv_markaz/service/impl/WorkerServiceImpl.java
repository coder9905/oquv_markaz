package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.entity.Worker;
import uz.zako.oquv_markaz.entity.Phone;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.WorkerPayload;
import uz.zako.oquv_markaz.repository.*;
import uz.zako.oquv_markaz.security.SecurityUtils;
import uz.zako.oquv_markaz.service.WorkerService;
import uz.zako.oquv_markaz.service.WorkerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {

    private final EmployesRepository employesRepository;
    private final CenterBranchesRepository centerBranchesRepository;
    private final AttachmentRepository attachmentRepository;
    private final WorkerRepository workerRepository;
    private final RoleRepository roleRepository;
    private final SecurityUtils securityUtils;
    private final PhoneRepository phoneRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?>  save(WorkerPayload payload){
        try {
            Worker worker=new Worker();
            worker.setFullName(payload.getFullName());
            List<Phone> phones=new ArrayList<>();
            for (int i = 0; i < payload.getPhones().size(); i++) {
                Phone phone = new Phone();
                phone.setPhone(payload.getPhones().get(i));
                phone = phoneRepository.save(phone);
                phones.add(phone);
            }
            worker.setAdress(payload.getAdress());
            worker.setPhones(phones);
            worker.setPosition(payload.getPosition());
            worker.setMonthly(payload.getMonthly());
            worker.setCenterBranches(centerBranchesRepository.findById(payload.getCenterBranchesId()).orElseThrow(()->new ResourceNotFoundException("CenterBranches not found")));
            worker=workerRepository.save(worker);
            if (worker != null) {
                return ResponseEntity.ok("save succesfull");
            }
            return new ResponseEntity(new Result(false,"error save worker",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error worker",e.getMessage());
            return new ResponseEntity(new Result(false,"worker saqlashda hatolik",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getOneWorkerId(Long id){
        try {
            Worker worker=workerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("worker not found"));
            return ResponseEntity.ok(new Result(true,"getOneWorkerId",worker));
        }catch (Exception e){
            log.error("error worker",e.getMessage());
            return new ResponseEntity(new Result(false,"getOneWorkerId error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getWorkerCenterBranchesId(int size, int page,Long id){
        try {
            Page<Worker> workers=workerRepository.getWorkerCenterBranchesId(PageRequest.of(page, size),id);
            if (workers != null){
                return ResponseEntity.ok(new Result(true,"get Worker centerBranchesId",workers));
            }
            return new ResponseEntity(new Result(false,"getWorkerCenterBranchesId employe error",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error worker",e.getMessage());
            return new ResponseEntity(new Result(false,"getWorkerCenterBranchesId employe error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getWorkerListCenterBranchesId(Long id){
        try {
            List<Worker> workers=workerRepository.getCenterBranchesId(id);
            if (workers != null){
                return ResponseEntity.ok(new Result(true,"get Worker centerBranchesId",workers));
            }
            return new ResponseEntity(new Result(false,"getWorkerCenterBranchesId employe error",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error worker",e.getMessage());
            return new ResponseEntity(new Result(false,"getWorkerCenterBranchesId employe error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllWorker(){
        try {
            List<Worker> workers=workerRepository.findAll();
            if (workers != null){
                return ResponseEntity.ok(new Result(true,"getAll Worker",workers));
            }
            return new ResponseEntity(new Result(false,"getAll Worker error",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error worker",e.getMessage());
            return new ResponseEntity(new Result(false,"getAll Worker error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> editWorker(WorkerPayload payload){
        try {
            Worker worker=workerRepository.findById(payload.getId()).orElseThrow(()->new ResourceNotFoundException("employe not found"));
            worker.setFullName(payload.getFullName());
            List<Phone> phones=new ArrayList<>();
            for (int i = 0; i < payload.getPhones().size(); i++) {
                Phone phone = new Phone();
                phone.setPhone(payload.getPhones().get(i));
                phone = phoneRepository.save(phone);
                phones.add(phone);
            }
            worker.setAdress(payload.getAdress());
            worker.setPosition(payload.getPosition());
            worker.setCenterBranches(centerBranchesRepository.findById(payload.getId()).orElseThrow(()->new ResourceNotFoundException("CenterBranches not found")));
            worker=workerRepository.save(worker);
            if (worker != null){
                return ResponseEntity.ok(new Result(true,"edit Worker",worker));
            }
            return new ResponseEntity(new Result(false,"edit worker error",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error worker",e.getMessage());
            return new ResponseEntity(new Result(false,"edit worker error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteWorker(Long workerId){
        try {
            Worker worker=workerRepository.findById(workerId).orElseThrow(()->new ResourceNotFoundException("worker not found"));
            workerRepository.deleteById(workerId);
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error("error worker",e.getMessage());
            return new ResponseEntity(new Result(false,"delete employe error",null), HttpStatus.BAD_REQUEST);
        }
    }


}
