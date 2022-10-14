package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerPayload {

    private Long id;

    private String fullName;

    private String adress;

    private List<String> phones;

    private String position;

    private Long monthly;

    private Long centerBranchesId;

    public WorkerPayload(String fullName, String adress, List<String> phones, Long monthly, Long centerBranchesId) {
        this.fullName = fullName;
        this.adress = adress;
        this.phones = phones;
        this.monthly = monthly;
        this.centerBranchesId = centerBranchesId;
    }

    public WorkerPayload(Long id, String fullName, String adress, Long monthly) {
        this.id = id;
        this.fullName = fullName;
        this.adress = adress;
        this.monthly = monthly;
    }
}
