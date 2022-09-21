package uz.zako.oquv_markaz.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.zako.oquv_markaz.entity.abstractEntity.AbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Employee extends AbstractEntity {

    private String fullName;

    private String phone;

    private String adress;

    private boolean isTeacher;

    private String seniority;

    private Long monthly;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment img;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private CenterBranches centerBranches;

}
