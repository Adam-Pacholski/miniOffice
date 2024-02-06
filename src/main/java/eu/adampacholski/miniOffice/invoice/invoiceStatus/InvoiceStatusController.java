package eu.adampacholski.miniOffice.invoice.invoiceStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/invoice_stat")
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

}
