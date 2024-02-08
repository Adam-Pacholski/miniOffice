package eu.adampacholski.miniOffice.invoice;


import eu.adampacholski.miniOffice.invoice.invoiceNrSetting.InvoiceNrSetting;
import eu.adampacholski.miniOffice.invoice.invoiceNrSetting.InvoiceNrSettingRepo;
import eu.adampacholski.miniOffice.invoice.invoiceType.InvoiceTypeRepo;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepo invoiceRepo;
    private final InvoiceNrSettingRepo invoiceNrSettingRepo;
    private final InvoiceTypeRepo invoiceTypeRepo;

    public InvoiceService(InvoiceRepo invoiceRepo, InvoiceNrSettingRepo invoiceNrSettingRepo, InvoiceTypeRepo invoiceTypeRepo) {
        this.invoiceRepo = invoiceRepo;
        this.invoiceNrSettingRepo = invoiceNrSettingRepo;
        this.invoiceTypeRepo = invoiceTypeRepo;
    }

    public List<Invoice> get() {
        return invoiceRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public List<Invoice> getAllByCustomerId(Long id){
        return invoiceRepo.findAllByCommentsId(id, Sort.by(Sort.Direction.DESC, "id"));
    }
    public Invoice add(Invoice item, Integer days) {
        LocalDateTime date = LocalDateTime.now();
        InvoiceNrSetting setting = null;
        Long typ= item.getInvoiceType().getId();
        if (typ == 1){
          setting  = invoiceNrSettingRepo.findById(1L).get();
        }
        if (typ == 2){
            setting  = invoiceNrSettingRepo.findById(2L).get();
        }
        if (typ == 3){
            setting  = invoiceNrSettingRepo.findById(3L).get();
        }
        if (typ == 4){
            setting  = invoiceNrSettingRepo.findById(4L).get();
        }

        item.setRisedDate(date);
        item.setTerminDate(date.plusDays(days));
        if (item.getInvoiceStatus().getId() == 1)
            item.setPaidDate(LocalDate.now());
        if (setting.getYear() < date.getYear()) {
            setting.setYear(date.getYear());
            setting.setNumber(1);
            invoiceNrSettingRepo.save(setting);
        }

        String invNumber = item.getInvoiceType().getCode() + "/" + setting.getNumber() + "/" + setting.getYear();
        item.setInvoiceNumber(invNumber);
        setting.setNumber(setting.getNumber()+1);
        return invoiceRepo.save(item);
    }
}
