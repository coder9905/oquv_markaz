package uz.zako.oquv_markaz.entity;

import lombok.*;
import uz.zako.oquv_markaz.entity.abstractEntity.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Payment extends AbstractEntity {

    private Long price;

    private String moon;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Discount discount;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Groups> groups;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users;

}
