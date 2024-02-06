package eu.adampacholski.miniOffice.invoice.InvoiceNrSetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceNrSettingService {
    private final InvoiceNrSettingRepo invoiceNrSettingRepo;
@Autowired
    public InvoiceNrSettingService(InvoiceNrSettingRepo invoiceNrSettingRepo) {
        this.invoiceNrSettingRepo = invoiceNrSettingRepo;
    }


    public InvoiceNrSetting getById(Long id){
        return invoiceNrSettingRepo.findById(id).get();
    }

    public InvoiceNrSetting update(InvoiceNrSetting invoiceNrSetting){
        return invoiceNrSettingRepo.save(invoiceNrSetting);
    }
}
