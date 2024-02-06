package eu.adampacholski.miniOffice.invoice;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepo invoiceRepo;

    public InvoiceService(InvoiceRepo invoiceRepo) {
        this.invoiceRepo = invoiceRepo;
    }

    public List<Invoice> get() {
        return invoiceRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

}
