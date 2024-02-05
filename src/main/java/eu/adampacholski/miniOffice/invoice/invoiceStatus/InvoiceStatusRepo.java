package eu.adampacholski.miniOffice.invoice.invoiceStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceStatusRepo extends JpaRepository<InvoiceStatus, Long> {


}
