package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.SalaryPayload;

public interface SalaryService {
    ResponseEntity<?> saveSalary(SalaryPayload payload);

    ResponseEntity<?> editSalary(Long salaryId, SalaryPayload payload);

    ResponseEntity<?> getAllSalary();

    ResponseEntity<?> getSalaryMoonId(Long moonId);

    ResponseEntity<?> deleteSalaryId(Long salaryId);

    ResponseEntity<?> deleteSalaryEmployeId(Long employeId);
}
