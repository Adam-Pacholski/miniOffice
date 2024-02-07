package eu.adampacholski.miniOffice.invoice.invoiceNrSetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/invoice-nr-setting")
public class InvoiceNrSettingController {
    private final InvoiceNrSettingService invoiceNrSettingService;

    @Autowired
    public InvoiceNrSettingController(InvoiceNrSettingService invoiceNrSettingService) {
        this.invoiceNrSettingService = invoiceNrSettingService;
    }

    @PostMapping("/add")
    public ResponseEntity<InvoiceNrSetting> add(
            @RequestBody InvoiceNrSetting item) {
        InvoiceNrSetting newItem = invoiceNrSettingService.add(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

}
