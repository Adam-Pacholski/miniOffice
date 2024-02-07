package eu.adampacholski.miniOffice.invoice.invoiceStatus;

import eu.adampacholski.miniOffice.item.itemWarehouse.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/invoice-stat")
public class InvoiceStatusController {

    private final InvoiceStatusService invoiceStatusService;

    @Autowired
    public InvoiceStatusController(InvoiceStatusService invoiceStatusService) {
        this.invoiceStatusService = invoiceStatusService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<InvoiceStatus>> get() {
        List<InvoiceStatus> list = invoiceStatusService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<InvoiceStatus> add(
            @RequestBody InvoiceStatus item) {
        InvoiceStatus newItem = invoiceStatusService.add(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

}
