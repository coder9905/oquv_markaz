package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Discount;
import uz.zako.oquv_markaz.entity.Week;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {


}
