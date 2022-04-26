package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.zako.oquv_markaz.entity.Groups;
import uz.zako.oquv_markaz.entity.User;

import java.util.List;
import java.util.Optional;

public interface GroupsRepository extends JpaRepository<Groups,Long> {

    @Query(nativeQuery = true, value = "select * from public.groups g where id=:id")
    Groups findByIdGroup(@Param("id") Long id);

    Groups findByName(String name);


    @Query(nativeQuery = true,value = "select g from public.groups g inner join groups_teachers t on g.id=t.groups_id  where t.teachers_id=:teachersId")
    List<User> getByTeacherIdGroup(@Param("teachersId") Long id);


}
