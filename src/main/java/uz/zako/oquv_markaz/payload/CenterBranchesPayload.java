package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CenterBranchesPayload {

    private Long id;

    private String name;

    private String phone;

    private String workingTime;

    private Long TrainingCenter;

    public CenterBranchesPayload(Long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
}
