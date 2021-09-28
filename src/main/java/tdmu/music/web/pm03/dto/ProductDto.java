package tdmu.music.web.pm03.dto;

import lombok.Data;
import tdmu.music.web.pm03.model.Category;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private int categoryId;

    private String price;
    private double weight;
    private String description;
    private String imageName;
}
