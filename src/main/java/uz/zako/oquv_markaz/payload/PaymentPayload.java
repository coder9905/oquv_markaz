package uz.zako.oquv_markaz.payload;

import lombok.*;
import uz.zako.oquv_markaz.entity.Discount;
import uz.zako.oquv_markaz.entity.Groups;
import uz.zako.oquv_markaz.entity.Moon;
import uz.zako.oquv_markaz.entity.User;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class PaymentPayload {

    private Long price;

    private String moon;

    private Long discountId;

    private Long groupsId;

    private Long userId;

}
