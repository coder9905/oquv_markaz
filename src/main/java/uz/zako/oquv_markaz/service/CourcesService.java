package uz.zako.oquv_markaz.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.CourcesPayload;
import uz.zako.oquv_markaz.payload.NewsPayload;

public interface CourcesService {

    ResponseEntity<?> addCources(CourcesPayload courcesPayload);

    Page<CourcesPayload> getPageCource(int page, int size);

    ResponseEntity<?> getAllCources();

    ResponseEntity<?> editCources(CourcesPayload payload);

    boolean deleteById(Long id);
}
