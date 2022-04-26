package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.CourcesPayload;
import uz.zako.oquv_markaz.payload.NewsPayload;

public interface CourcesService {

    ResponseEntity<?> addCources(CourcesPayload courcesPayload);

    ResponseEntity<?> getAllCources();

    ResponseEntity<?> editCources(CourcesPayload payload);

    boolean deleteById(Long id);
}
