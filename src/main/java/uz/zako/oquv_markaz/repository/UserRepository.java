package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(nativeQuery = true, value = "select * from public.users u where u.id=:id")
    User findByIdUser(@Param("id") Long id);

    User getById(Long id);

    @Query(nativeQuery = true, value = "select * from users u where u.center_branches_id=?1")
    List<User> getByGroupIdUsers(Long id);

    @Query(nativeQuery = true, value = "select * from users u where u.center_branches_id=?1")
    List<User> getBycenterBranchesIdUsers(Long id);

    @Query(nativeQuery = true, value = "delete from users u where u.group_id=?1")
    void deleteGroupIdUser(Long id);

    @Query(nativeQuery = true, value = "delete from users u where u.center_branches_id=?1")
    void deleteCenterBranchesIdUser(Long id);


}
