package uz.zako.oquv_markaz.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import uz.zako.oquv_markaz.entity.abstractEntity.AbstractEntity;

import javax.persistence.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class News extends AbstractEntity {

    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category categorys;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Attachment img;

}
