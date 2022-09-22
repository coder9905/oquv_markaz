package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import uz.zako.oquv_markaz.entity.Attachment;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.entity.Subject;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
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
}
