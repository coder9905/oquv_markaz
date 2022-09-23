package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Discount;
import uz.zako.oquv_markaz.entity.Week;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query(nativeQuery = true, value = "select * from Discount d where d.group_id=?1")
    List<Discount> getDiscountGroupId(Long id);

    @Query(nativeQuery = true, value = "delete from Discount d where d.group_id=?1")
    void deleteDiscountGroupId(Long id);


}
