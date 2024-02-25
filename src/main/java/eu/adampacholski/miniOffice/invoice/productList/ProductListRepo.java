package eu.adampacholski.miniOffice.invoice.productList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductListRepo extends JpaRepository<ProductList, Long> {

    @Query("SELECT s FROM ProductList s WHERE s.invoice.id = ?1")
    List<ProductList> findProductListsByInvoiceId(Long id);

}
