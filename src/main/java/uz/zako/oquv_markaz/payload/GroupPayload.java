package uz.zako.oquv_markaz.payload;

import lombok.*;
import uz.zako.oquv_markaz.entity.Attachment;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.entity.Subject;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupPayload {

    private Long id;

    private String name;

    private Long price;

    private String title;

    private String discription;

    private String hashId;

    private String duration;

    private Long capacity;

    private Long subjectId;

    private Long employeesId;

    public GroupPayload(String name, Long price, String title, String discription, String duration, Long capacity, Long subjectId, Long employeesId) {
        this.name = name;
        this.price = price;
        this.title = title;
        this.discription = discription;
        this.duration = duration;
        this.capacity = capacity;
        this.subjectId = subjectId;
        this.employeesId = employeesId;
    }
}
