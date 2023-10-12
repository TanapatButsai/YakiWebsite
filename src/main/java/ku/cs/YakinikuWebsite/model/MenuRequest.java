package ku.cs.YakinikuWebsite.model;

import lombok.Data;

import java.util.UUID;

@Data
public class MenuRequest {
    private String name;
    private double price;
    private UUID categoryId;
    private String menuImage;
}
