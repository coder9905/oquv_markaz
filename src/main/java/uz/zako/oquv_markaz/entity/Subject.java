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
public class Subject extends AbstractEntity {

    private String name;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String discription;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Attachment img;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private CenterBranches centerBranches;

}
