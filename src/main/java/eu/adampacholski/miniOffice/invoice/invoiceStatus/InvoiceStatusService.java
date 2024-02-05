package eu.adampacholski.miniOffice.invoice.invoiceStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceStatusService {

    private final InvoiceStatusRepo invoiceStatusRepo;
    @Autowired
    public InvoiceStatusService(InvoiceStatusRepo invoiceStatusRepo) {
        this.invoiceStatusRepo = invoiceStatusRepo;
    }

    public List<InvoiceStatus> getAll(){
        return invoiceStatusRepo.findAll(Sort.by(Sort.Direction.ASC, "stat"));
    }
}
