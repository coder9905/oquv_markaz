package uz.zako.oquv_markaz.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.zako.oquv_markaz.entity.abstractEntity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,columnDefinition = "TEXT")//hohlagancha yoza olasz cheksiz
    private String username;

    private String password;

    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
//    @JsonIgnore bizga olib beriwni berkitib qoyadi
    private List<Role> roles;

    private boolean isAdmin;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy ="users")
    @JsonBackReference
    private List<Groups> group;

}
