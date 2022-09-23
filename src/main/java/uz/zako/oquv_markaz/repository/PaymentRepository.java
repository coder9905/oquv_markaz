package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Outputs;
import uz.zako.oquv_markaz.entity.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(nativeQuery = true, value = "select * from Payment p where p.user_id=?1")
    List<Payment> getPaymentUSerId(Long id);

    @Query(nativeQuery = true, value = "select * from Payment p where p.moon_id=?1")
    List<Payment> getPaymenMoonId(Long id);

    @Query(nativeQuery = true, value = "select * from Payment p where p.group_id=?1")
    List<Payment> getPaymenGroupId(Long id);

    @Query(nativeQuery = true, value = "delete from Payment p where p.group_id=?1")
    void deletePaymenGroupId(Long id);






}
