package uz.zako.oquv_markaz.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import uz.zako.oquv_markaz.entity.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutputPayload {

    private Long id;

    private String title;

    private String discription;

    private String time;

    private Long price;

    private Long userId;

}
