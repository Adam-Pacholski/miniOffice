package eu.adampacholski.miniOffice.invoice.invoiceType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceTypeService {
    private final InvoiceTypeRepo invoiceTypeRepo;

    @Autowired
    public InvoiceTypeService(InvoiceTypeRepo invoiceTypeRepo) {
        this.invoiceTypeRepo = invoiceTypeRepo;
    }
    public List<InvoiceType> get() {
        return invoiceTypeRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
    public InvoiceType add(InvoiceType item) {
        return invoiceTypeRepo.save(item);
    }
}
