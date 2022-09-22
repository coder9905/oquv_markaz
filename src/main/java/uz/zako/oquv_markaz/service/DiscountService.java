package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.DiscountPayload;

public interface DiscountService {
    ResponseEntity<?> saveDiscount(Long groupId, DiscountPayload payload);

    ResponseEntity<?> editDiscount(Long groupId, DiscountPayload payload);
}
