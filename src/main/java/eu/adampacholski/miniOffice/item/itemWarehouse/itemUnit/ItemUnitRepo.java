package eu.adampacholski.miniOffice.item.itemWarehouse.itemUnit;

import eu.adampacholski.miniOffice.item.itemWarehouse.itemCategory.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemUnitRepo extends JpaRepository<ItemUnit, Long> {
    @Query("SELECT s FROM ItemUnit s WHERE s.name = ?1")
    Optional<ItemUnit> findItemByName(String name);

    @Query("SELECT s FROM ItemUnit s WHERE s.name = ?1 AND NOT s.id = ?2")
    Optional<ItemUnit> findItemByNameAndNotId(String name, Long id);
}
