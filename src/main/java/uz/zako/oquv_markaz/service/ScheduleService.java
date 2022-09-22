package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.SchedulePayload;

public interface ScheduleService {
    ResponseEntity<?> save(SchedulePayload payload, Long teacherId);

    ResponseEntity<?> getScheduleTeacherId(Long id);

    ResponseEntity<?> getScheduleWeekId(Long id);

    ResponseEntity<?> getAllSchedule();
}
