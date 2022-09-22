package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.entity.Moon;
import uz.zako.oquv_markaz.payload.EmployePayload;

import java.util.List;

@Repository
public interface MoonRepository extends JpaRepository<Moon, Long> {


}
