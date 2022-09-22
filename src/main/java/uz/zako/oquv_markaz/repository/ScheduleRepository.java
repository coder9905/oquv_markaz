package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.entity.Schedule;
import uz.zako.oquv_markaz.payload.EmployePayload;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(nativeQuery = true, value = "select * from Schedule s where s.teacher_id=?1")
    List<Schedule> getScheduleTeacherId(Long id);

    @Query(nativeQuery = true, value = "select * from Schedule s where s.week_id=?1")
    List<Schedule> getScheduleWeekId(Long id);


}
