package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import uz.zako.oquv_markaz.entity.TrainingCenter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class BlockPayload {

    private Long id;

    private Long trainingCenterId;

}
