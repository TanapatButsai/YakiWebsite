package ku.cs.YakinikuWebsite.entity;

import jakarta.persistence.*;
import ku.cs.YakinikuWebsite.status.DiscountStatus;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Discount {

    @Id
    @GeneratedValue
    private UUID id;

    private double percentDiscount;

    private String discountName;

    private DiscountStatus discountStatus;


}
