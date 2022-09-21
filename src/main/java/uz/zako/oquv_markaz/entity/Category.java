package uz.zako.oquv_markaz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.zako.oquv_markaz.entity.abstractEntity.AbstractEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category extends AbstractEntity {

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private CenterBranches center_branches;

}
