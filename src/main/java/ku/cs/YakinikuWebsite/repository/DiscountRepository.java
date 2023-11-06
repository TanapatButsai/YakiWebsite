package ku.cs.YakinikuWebsite.repository;

import ku.cs.YakinikuWebsite.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, UUID> {
    Discount findByDiscountName(String discountName);

}
