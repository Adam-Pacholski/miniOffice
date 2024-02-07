package eu.adampacholski.miniOffice.invoice.invoiceNrSetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceNrSettingService {
    private final InvoiceNrSettingRepo invoiceNrSettingRepo;
@Autowired
    public InvoiceNrSettingService(InvoiceNrSettingRepo invoiceNrSettingRepo) {
        this.invoiceNrSettingRepo = invoiceNrSettingRepo;
    }

    public InvoiceNrSetting add(InvoiceNrSetting item) {
    return invoiceNrSettingRepo.save(item);
    }
}
