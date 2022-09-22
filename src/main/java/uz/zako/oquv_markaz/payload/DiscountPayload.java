package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class DiscountPayload {

    private Long id;

    private Long time;

    private Long foizi;

    private Long groupId;
}
