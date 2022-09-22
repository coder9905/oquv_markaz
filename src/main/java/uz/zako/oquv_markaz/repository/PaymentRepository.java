package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Payment;
import uz.zako.oquv_markaz.entity.Week;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {


}
