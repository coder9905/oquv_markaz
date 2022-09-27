package uz.zako.oquv_markaz.payload;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import uz.zako.oquv_markaz.entity.Phone;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrainingCenterPayload {

    private Long id;
    private String name;
    private List<String> phone;
    private boolean block;
    private String workingTime;
    private Date createdAt;
    private Date updatedAt;

    public TrainingCenterPayload(Long id, String name, boolean block, String workingTime, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.block = block;
        this.workingTime = workingTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
