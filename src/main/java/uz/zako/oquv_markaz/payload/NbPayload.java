package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class NbPayload {

    private Long id;

    private Long userId;

    private Long adminId;

    public NbPayload(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }
}
