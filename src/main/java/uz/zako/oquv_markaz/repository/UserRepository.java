package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.User;
import uz.zako.oquv_markaz.payload.UserPayload;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(nativeQuery = true, value = "select * from public.users u where u.id=:id")
    User findByIdUser(@Param("id") Long id);

    User getById(Long id);

    @Query(nativeQuery = true, value = "select * from users u where u.center_branches_id=?1")
    List<User> getByUsersGroupId(Long id);

    @Query("select new uz.zako.oquv_markaz.payload.UserPayload(u.id,u.username,u.password,u.fullName,u.adress) from users u inner join u.group ug inner join u.centerBranches uc inner join Phone p where ug.id=?1 and uc.id=?2")
    List<UserPayload> getByUsersGroupId(Long groupId, Long centerBranchesId);


    @Query(nativeQuery = true, value = "select * from users u where u.center_branches_id=?1")
    List<User> getBycenterBranchesIdUsers(Long id);

    @Query(nativeQuery = true, value = "delete from users u where u.group_id=?1")
    void deleteGroupIdUser(Long id);

    @Query(nativeQuery = true, value = "delete from users u where u.center_branches_id=?1")
    void deleteCenterBranchesIdUser(Long id);


}
