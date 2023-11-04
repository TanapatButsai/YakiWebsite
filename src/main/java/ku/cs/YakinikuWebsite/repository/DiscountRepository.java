package ku.cs.YakinikuWebsite.repository;

import ku.cs.YakinikuWebsite.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiscountRepository extends JpaRepository<Discount, UUID> {

    Discount findByDiscountName(String discountName);

}
