package uz.zako.oquv_markaz.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import uz.zako.oquv_markaz.entity.abstractEntity.AbstractEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Entity
public class Subject extends AbstractEntity {

    private String name;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String discription;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Attachment img;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CenterBranches centerBranches;

}
