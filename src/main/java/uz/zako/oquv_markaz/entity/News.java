package uz.zako.oquv_markaz.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.zako.oquv_markaz.entity.abstractEntity.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class News extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(unique = true)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Categorys categorys;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Attachment img;

}
