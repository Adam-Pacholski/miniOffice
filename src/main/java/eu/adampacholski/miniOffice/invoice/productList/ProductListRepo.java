package eu.adampacholski.miniOffice.invoice.productList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductListRepo extends JpaRepository<ProductList, Long> {
}
