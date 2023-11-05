package ku.cs.YakinikuWebsite.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Member {


    @Id
    @GeneratedValue
    private UUID id;
    private String role;
    private String username;
    private String password;
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    @OneToMany(mappedBy = "member") // "member" is the name of the field in the PurchaseOrder class
    private List<PurchaseOrder> purchaseOrders = new ArrayList<>();


}
