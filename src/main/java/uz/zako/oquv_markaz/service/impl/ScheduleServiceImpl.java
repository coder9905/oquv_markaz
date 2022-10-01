package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.entity.Schedule;
import uz.zako.oquv_markaz.entity.Week;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.EmployePayload;
import uz.zako.oquv_markaz.payload.SchedulePayload;
import uz.zako.oquv_markaz.repository.*;
import uz.zako.oquv_markaz.service.EmployeeService;
import uz.zako.oquv_markaz.service.ScheduleService;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final EmployesRepository employesRepository;
    private final ScheduleRepository scheduleRepository;
    private final WeekRepository weekRepository;

    @Override
    public ResponseEntity<?>  save(SchedulePayload payload, Long teacherId){
        try {
            Schedule schedule=new Schedule();
            schedule.setFirstTime(payload.getFirstTime());
            schedule.setFinishTime(payload.getFinishTime());
            schedule.setWeeks(Arrays.asList(weekRepository.findById(payload.getWeekId()).orElseThrow(()->new ResourceNotFoundException("week not found"))));
            schedule.setEmployee(employesRepository.findById(teacherId).orElseThrow(()->new ResourceNotFoundException("employe not found")));
            schedule=scheduleRepository.save(schedule);
            if (schedule != null) {
                return ResponseEntity.ok("save succesfull");
            }
            return new ResponseEntity(new Result(false,"error save schedule",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error schedule",e.getMessage());
            return new ResponseEntity(new Result(false,"schedule saqlashda hatolik",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getScheduleTeacherId(int page, int size,Long id){
        try {
            Page<Schedule> schedules=scheduleRepository.getScheduleTeacherId(PageRequest.of(page, size),id);
            return ResponseEntity.ok(new Result(true,"getOneEmploye",schedules));
        }catch (Exception e){
            log.error("error schedules",e.getMessage());
            return new ResponseEntity(new Result(false,"get One schedules error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getScheduleWeekId(int page, int size, Long id){
        try {
            List<Schedule> schedules=scheduleRepository.getScheduleWeekId(id);
            if (schedules != null){
                return ResponseEntity.ok(new Result(true,"get schedules",schedules));
            }
            return new ResponseEntity(new Result(false,"getEmployeCenterBranchesId employe error",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error schedules",e.getMessage());
            return new ResponseEntity(new Result(false,"schedules  error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllSchedule(){
        try {
            List<Schedule> schedules=scheduleRepository.findAll();
            if (schedules != null){
                return ResponseEntity.ok(new Result(true,"getAll schedules",schedules));
            }
            return new ResponseEntity(new Result(false,"getAll schedules error",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error schedules",e.getMessage());
            return new ResponseEntity(new Result(false,"getAll schedules error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
