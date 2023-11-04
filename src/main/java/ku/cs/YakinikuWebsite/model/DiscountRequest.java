package ku.cs.YakinikuWebsite.model;

import lombok.Data;

@Data
public class DiscountRequest {

    private double percentDiscount;

    private String discountName;
}
