package eu.adampacholski.miniOffice.invoice.invoiceNrSetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
    public InvoiceNrSetting edit(InvoiceNrSetting item) {
    return invoiceNrSettingRepo.save(item);
    }
}
