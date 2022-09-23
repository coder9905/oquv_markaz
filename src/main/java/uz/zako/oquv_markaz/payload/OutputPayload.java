package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import uz.zako.oquv_markaz.entity.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@Getter
@Setter
@Data
public class OutputPayload {

    private Long id;

    private String title;

    private String discription;

    private Long price;

    private Long userId;

}
