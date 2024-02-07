package eu.adampacholski.miniOffice.invoice.invoiceNrSetting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceNrSettingRepo extends JpaRepository<InvoiceNrSetting, Long> {
}
