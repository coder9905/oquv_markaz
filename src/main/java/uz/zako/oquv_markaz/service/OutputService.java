package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.OutputPayload;

public interface OutputService {
    ResponseEntity<?> saveOutput(OutputPayload payload);

    ResponseEntity<?> editDiscount(Long outputId,OutputPayload payload);

    ResponseEntity<?> getAllOutput();

    ResponseEntity<?> getOutputGroupId(Long userId);

    ResponseEntity<?> deleteOutputuserId(Long outputId);
}
