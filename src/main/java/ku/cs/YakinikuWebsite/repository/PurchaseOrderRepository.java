package ku.cs.YakinikuWebsite.repository;

import ku.cs.YakinikuWebsite.entity.PurchaseOrder;
import ku.cs.YakinikuWebsite.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;


@Repository
public interface PurchaseOrderRepository
        extends JpaRepository<PurchaseOrder, UUID> {

    List<PurchaseOrder> getAllByStatusNot(Status status);
    PurchaseOrder getByMemberUsername(String username);

    List<PurchaseOrder> getAllByMemberUsernameAndStatusNot(String username,Status status);
}

