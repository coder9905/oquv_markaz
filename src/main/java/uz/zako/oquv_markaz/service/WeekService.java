package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.WeekPayload;

public interface WeekService {
    ResponseEntity<?> saveWeek(WeekPayload payload);

    ResponseEntity<?> editWeek(Long weekId, WeekPayload payload);

    ResponseEntity<?> getAllWeek();
}
