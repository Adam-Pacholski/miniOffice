package eu.adampacholski.miniOffice.invoice;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Long> {
    @Query("SELECT s FROM Invoice s WHERE s.customer.id = ?1")
    List<Invoice> findAllByCommentsId(Long id, Sort sort);

}
