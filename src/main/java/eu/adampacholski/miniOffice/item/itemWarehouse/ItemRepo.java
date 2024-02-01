package eu.adampacholski.miniOffice.item.itemWarehouse;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {

    @Query("SELECT s FROM Item s WHERE s.partNumber = ?1")
    Optional<Item> findByPartNumber(String name);
    @Query("SELECT s FROM Item s WHERE s.partNumber= ?1 AND NOT s.id = ?2")
    Optional<Item> findItemByPartNumberAndOntId(String name, Long id);

    @Query("SELECT s FROM Item s WHERE s.itemCategory.name = ?1")
    List<Item> findItemsByCategory(String cat, Sort sort);
}
