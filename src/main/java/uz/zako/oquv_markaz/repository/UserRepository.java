package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.zako.oquv_markaz.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    @Query(nativeQuery = true,value = "select u.id,u.fullName,u.username,u.isAdmin from users u inner join user_groups g on u.id=g.users_id  where g.groups_id=:groupsId")
    List<User> getByGroupIdUsers(@Param("groupsId") Long id);

}
