package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.zako.oquv_markaz.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    @Query(nativeQuery = true, value = "select * from public.users u where u.id=:id")
    User findByIdUser(@Param("id") Long id);

    User getById(Long id);

    @Query(nativeQuery = true, value = "select * from users u inner join users_group ug on ug.users_id=u.id where ug.group_id=?1")
    List<User> getByGroupIdUsers(Long id);

}
