package uz.zako.oquv_markaz.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;
import uz.zako.oquv_markaz.entity.abstractEntity.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Entity
public class Groups extends AbstractEntity {

    @Column(unique = true, length = 50)
    private String name;

    private Long price;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String discription;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment img;

    private String duration;

    private Long capacity;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Subject> subject;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Employee> employees;


}
