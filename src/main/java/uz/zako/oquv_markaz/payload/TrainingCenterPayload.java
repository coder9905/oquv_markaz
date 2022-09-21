package uz.zako.oquv_markaz.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrainingCenterPayload {

    private Long id;
    private String name;
    private String phone;
}
