package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.CategoryPayload;
import uz.zako.oquv_markaz.payload.TrainingCenterPayload;

public interface TrainingCenterService {
    ResponseEntity<?> save(TrainingCenterPayload payload);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<?> edit(TrainingCenterPayload payload, Long id);

    ResponseEntity<?> getOne(Long id);

    ResponseEntity<?> getAll();

    ResponseEntity<?> getAllPage(int page, int size);

    ResponseEntity<?> getUserTokenTrainingCenter();

    ResponseEntity<?> savetrainingCenterBlock(Long trainingCenterId);

    ResponseEntity<?> deletetrainingCenterBlock(Long trainingCenterId);
}
