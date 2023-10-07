package ku.cs.YakinikuWebsite.entity;

import jakarta.persistence.*;
import ku.cs.YakinikuWebsite.status.Status;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Member member;

    private LocalDateTime timestamp;
    private Status status;

    public double getTotal() {
        double total = 0;
        for (OrderItem item : items)
            total += item.getSubtotal();
        return total;
    }

    @OneToMany(mappedBy = "purchaseOrder")
    private List<OrderItem> items = new ArrayList<>();
}
