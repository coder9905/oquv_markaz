package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.entity.Nb;
import uz.zako.oquv_markaz.payload.EmployePayload;
import uz.zako.oquv_markaz.payload.NbPayload;

import java.util.List;

@Repository
public interface NbRepository extends JpaRepository<Nb, Long> {

    @Query("select new uz.zako.oquv_markaz.payload.NbPayload(n.id, nu.id) from Nb n inner join n.user nu where nu.id=?1")
    List<NbPayload> getUserIdNb(Long id);

//    @Query(nativeQuery = true, value = "select * from Nb n where n.user_id=?1")
//    List<NbPayload> getUserIdNb(Long id);


}
