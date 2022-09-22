package uz.zako.oquv_markaz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.zako.oquv_markaz.entity.abstractEntity.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends AbstractEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(unique = true, columnDefinition = "TEXT")//hohlagancha yoza olasz cheksiz
    private String username;

    private String password;

    private String fullName;

    private String phone;

    private String adress;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment img;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private List<Role> roles;

    private boolean isAdmin;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Groups> group;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<CenterBranches> centerBranches;

}
