package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CategoryPayload {

    private Long id;
    private String name;

    public CategoryPayload(String name) {
        this.name = name;
    }

    public CategoryPayload() {
    }
}
