package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Salary;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

    @Query(nativeQuery = true, value = "select * from Salary s where s.user_id=?1")
    List<Salary> getSalaryUSerId(Long id);

    @Query(nativeQuery = true, value = "select * from Salary s where s.moon_id=?1")
    List<Salary> getSalaryMoonId(Long id);

    @Query(nativeQuery = true, value = "select * from Salary s where s.group_id=?1")
    List<Salary> getSalaryGroupId(Long id);

    @Query(nativeQuery = true, value = "delete from Salary s where s.employee_id=?1")
    void deleteSalaryEmployeId(Long id);


}
