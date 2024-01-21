package eu.adampacholski.miniOffice.item.itemWarehouse;

import eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse.ItemWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {

    @Query("SELECT s FROM Item s WHERE s.name = ?1")
    Optional<Item> findItemByName(String name);
    @Query("SELECT s FROM Item s WHERE s.partNumber= ?1")
    Optional<Item> findItemByPartName(String name);
}
