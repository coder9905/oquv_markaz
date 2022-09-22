package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.entity.Week;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.EmployePayload;
import uz.zako.oquv_markaz.payload.WeekPayload;
import uz.zako.oquv_markaz.repository.AttachmentRepository;
import uz.zako.oquv_markaz.repository.CenterBranchesRepository;
import uz.zako.oquv_markaz.repository.EmployesRepository;
import uz.zako.oquv_markaz.repository.WeekRepository;
import uz.zako.oquv_markaz.service.EmployeeService;
import uz.zako.oquv_markaz.service.WeekService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeekServiceImpl implements WeekService {

    private final WeekRepository weekRepository;

    @Override
    public ResponseEntity<?> saveWeek(WeekPayload payload){
        try {
            Week week=new Week();
            week.setName(payload.getName());
            week=weekRepository.save(week);
            if (week != null){
                return ResponseEntity.ok(new Result(true,"save succesfull",week));
            }
            return new ResponseEntity(new Result(false,"save error week",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error week",e.getMessage());
            return new ResponseEntity(new Result(false,"save error week",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> editWeek(Long weekId, WeekPayload payload){
        try {
            Week week=weekRepository.findById(weekId).orElseThrow(()->new ResourceNotFoundException("week not found"));
            week.setName(payload.getName());
            week=weekRepository.save(week);
            if (week != null){
                return ResponseEntity.ok(new Result(true,"edit succesfull",week));
            }
            return new ResponseEntity(new Result(false,"edit error week",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error week",e.getMessage());
            return new ResponseEntity(new Result(false,"edit error week",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllWeek(){
        try {
            List<Week> weeks=weekRepository.findAll();
            return ResponseEntity.ok(weeks);
        }catch (Exception e){
            log.error("error week",e.getMessage());
            return new ResponseEntity(new Result(false,"edit error week",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
