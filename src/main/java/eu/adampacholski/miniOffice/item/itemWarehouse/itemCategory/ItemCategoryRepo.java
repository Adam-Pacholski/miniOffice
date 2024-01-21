package eu.adampacholski.miniOffice.item.itemWarehouse.itemCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemCategoryRepo extends JpaRepository<ItemCategory, Long> {
    @Query("SELECT s FROM ItemCategory s WHERE s.name = ?1")
    Optional<ItemCategory> findItemCategoryByName(String name);
}
