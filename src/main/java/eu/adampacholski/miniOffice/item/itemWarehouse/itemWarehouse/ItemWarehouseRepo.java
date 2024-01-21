package eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemWarehouseRepo extends JpaRepository<ItemWarehouse, Long> {
    @Query("SELECT s FROM ItemWarehouse s WHERE s.name = ?1")
    Optional<ItemWarehouse> findItemWarehouseByName(String name);
}
