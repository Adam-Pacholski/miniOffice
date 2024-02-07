package eu.adampacholski.miniOffice.invoice.invoiceStatus;

import eu.adampacholski.miniOffice.Exception.NotFoundException;
import eu.adampacholski.miniOffice.item.itemWarehouse.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public InvoiceStatus add(InvoiceStatus item) {
        return invoiceStatusRepo.save(item);
    }
}
