package ku.cs.YakinikuWebsite.repository;

import ku.cs.YakinikuWebsite.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu,UUID> {
}
