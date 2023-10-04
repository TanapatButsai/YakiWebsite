package ku.cs.YakinikuWebsite.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import ku.cs.YakinikuWebsite.status.Role;
import lombok.Getter;

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

}
