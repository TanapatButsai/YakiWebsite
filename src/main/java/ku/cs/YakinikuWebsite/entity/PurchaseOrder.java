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


    private LocalDateTime timestamp;
    private Status status;
    //private String name;


    //private UUID uuid;
    public double getTotal() {
        double total = 0;
        for (OrderItem item : items)
            total += item.getSubtotal();
        return total;
    }

    public boolean checkMinimumPrice() {
        double total = 0;
        for (OrderItem item : items)
            total += item.getSubtotal();
        return total > 150;
    }




    @ManyToOne
    @JoinColumn(name = "member_id") // "member_id" is the foreign key column in the PurchaseOrder table
    private Member member;

    @OneToOne
    private Discount discount;




    @OneToMany(mappedBy = "purchaseOrder")
    private List<OrderItem> items = new ArrayList<>();

    public boolean isDelivered(){
        if(status.equals(Status.DELIVERED)){
            return true;
        }
        return false;

    }
}
