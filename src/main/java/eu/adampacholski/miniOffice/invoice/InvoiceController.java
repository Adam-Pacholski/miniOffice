package eu.adampacholski.miniOffice.invoice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Invoice>> getAllByCustomerId(
            @PathVariable("id") Long id
    ) {
        List<Invoice> items = invoiceService.getAllByCustomerId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    @PostMapping("/add/{days}")
    public ResponseEntity<Invoice> add(
            @PathVariable("days") Integer days,
            @RequestBody Invoice item) {

        Invoice newItem = invoiceService.add(item,days);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

}
