package eu.adampacholski.miniOffice.invoice;

import eu.adampacholski.miniOffice.item.itemWarehouse.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Invoice>> get() {
        List<Invoice> items = invoiceService.get();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
