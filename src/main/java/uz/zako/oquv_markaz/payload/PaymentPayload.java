package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
@Data
public class PaymentPayload {

    private Long id;

    private Long price;

    private Long moonId;

    private Long discountId;

    private Long groupsId;

    private Long usersId;

}
