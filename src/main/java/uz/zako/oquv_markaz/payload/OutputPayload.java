package uz.zako.oquv_markaz.payload;

import lombok.*;
import uz.zako.oquv_markaz.entity.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class OutputPayload {

    private Long id;

    private String title;

    private String discription;

    private Long price;

    private Long userId;

}
