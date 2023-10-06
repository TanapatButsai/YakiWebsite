package ku.cs.YakinikuWebsite.repository;

import ku.cs.YakinikuWebsite.entity.OrderItem;
import ku.cs.YakinikuWebsite.entity.OrderItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepository
        extends JpaRepository<OrderItem, OrderItemKey> {
}
