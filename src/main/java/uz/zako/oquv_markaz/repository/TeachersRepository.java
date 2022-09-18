package uz.zako.oquv_markaz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.zako.oquv_markaz.entity.Cource;
import uz.zako.oquv_markaz.entity.Teacher;
import uz.zako.oquv_markaz.payload.NewsPayload;
import uz.zako.oquv_markaz.payload.TeacherPayload;

public interface TeachersRepository extends JpaRepository<Teacher, Long> {

    @Query("select new uz.zako.oquv_markaz.payload.TeacherPayload(n.id,n.fullName,n.phone,n.img.hashId) from Teacher n")
    Page<TeacherPayload> findAllByPage(Pageable pageable);

    Teacher getById(Long id);


}
