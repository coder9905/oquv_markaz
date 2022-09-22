package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.NbPayload;

public interface NbService {
    ResponseEntity<?> save(NbPayload payload);

    ResponseEntity<?> getOne(Long userId);
}
