package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.PaymentPayload;
import uz.zako.oquv_markaz.payload.WeekPayload;

public interface PaymentService {
    ResponseEntity<?> savePayment(PaymentPayload payload);

    ResponseEntity<?> editPayment(Long paymentId, PaymentPayload payload);

    ResponseEntity<?> getAllPayment();

    ResponseEntity<?> getPaymentUserId(Long userId);

    ResponseEntity<?> getPaymentMoonId(Long moonId);

    ResponseEntity<?> getPaymentGroupId(Long groupId);

    ResponseEntity<?> deletePaymentId(Long paymentId);

    ResponseEntity<?> deletePaymentGroupId(Long groupId);
}
