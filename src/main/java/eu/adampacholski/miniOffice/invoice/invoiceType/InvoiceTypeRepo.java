package eu.adampacholski.miniOffice.invoice.invoiceType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceTypeRepo extends JpaRepository<InvoiceType, Long> {
}
