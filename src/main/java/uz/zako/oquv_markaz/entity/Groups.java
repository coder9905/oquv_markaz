package uz.zako.oquv_markaz.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.zako.oquv_markaz.entity.abstractEntity.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Groups extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,length = 50)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Teacher> teachers;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cource cource;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users;
}
