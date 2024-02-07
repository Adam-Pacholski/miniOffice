package eu.adampacholski.miniOffice.invoice;

import eu.adampacholski.miniOffice.Exception.NotFoundException;
import eu.adampacholski.miniOffice.item.itemWarehouse.Item;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepo invoiceRepo;

    public InvoiceService(InvoiceRepo invoiceRepo) {
        this.invoiceRepo = invoiceRepo;
    }

    public List<Invoice> get() {
        return invoiceRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
    public Invoice add(Invoice item) {
//        Optional<Item> _item = invoiceRepo.findByPartNumber(item.getPartNumber());
//        if (_item.isPresent())
//            throw new NotFoundException("Podana część już istnieje");
        return invoiceRepo.save(item);
    }
}
