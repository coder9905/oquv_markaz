package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Discount;
import uz.zako.oquv_markaz.entity.Outputs;

import java.util.List;

@Repository
public interface OutputRepository extends JpaRepository<Outputs, Long> {

    @Query(nativeQuery = true, value = "select * from Outputs o where o.user_id=?1")
    List<Outputs> getOutputUserId(Long id);

    @Query(nativeQuery = true, value = "delete from Outputs o where o.user_id=?1")
    void deleteOutputUserId(Long id);

}
