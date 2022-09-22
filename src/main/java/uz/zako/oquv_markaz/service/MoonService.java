package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.MoonPayload;
import uz.zako.oquv_markaz.payload.WeekPayload;

public interface MoonService {
    ResponseEntity<?> saveMoon(MoonPayload payload);

    ResponseEntity<?> editMoon(Long moonId, MoonPayload payload);

    ResponseEntity<?> getAllMoon();
}
