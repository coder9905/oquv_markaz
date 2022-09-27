package uz.zako.oquv_markaz.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor

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
