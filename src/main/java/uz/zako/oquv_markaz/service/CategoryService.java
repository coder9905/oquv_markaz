package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.CategoryPayload;

public interface CategoryService {
    ResponseEntity<?> addCategory(CategoryPayload payload);

    ResponseEntity<?> editCategory(CategoryPayload payload);

    ResponseEntity<?> getIdCategory(Long id);

    ResponseEntity<?> getAllCategorys();

    boolean deleteById(Long id);
}
