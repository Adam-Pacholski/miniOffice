package eu.adampacholski.miniOffice.invoice.invoiceType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/invoice-type")
public class InvoiceTypeController {
    private final InvoiceTypeService invoiceTypeService;

    @Autowired
    public InvoiceTypeController(InvoiceTypeService invoiceTypeService) {
        this.invoiceTypeService = invoiceTypeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<InvoiceType>> get() {
        List<InvoiceType> item = invoiceTypeService.get();
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<InvoiceType> add(
            @RequestBody InvoiceType item) {
        InvoiceType newItem = invoiceTypeService.add(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }
}
