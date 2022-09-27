package uz.zako.oquv_markaz.payload;

import lombok.*;
import uz.zako.oquv_markaz.entity.TrainingCenter;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class BlockPayload {

    private Long id;

    private boolean Block;

    private Long trainingCenterId;

}
