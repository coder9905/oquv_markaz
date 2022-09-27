package uz.zako.oquv_markaz.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class DiscountPayload {

    private Long id;

    private Long time;

    private Long foizi;

    private Long groupId;
}
