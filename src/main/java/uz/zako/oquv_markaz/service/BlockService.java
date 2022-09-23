package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;

public interface BlockService {
    ResponseEntity<?> saveBlock(Long trainingCenterId);

    ResponseEntity<?> deleteBlock(Long blockId);

    ResponseEntity<?> getAllBlock();
}
