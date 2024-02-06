package eu.adampacholski.miniOffice.invoice.InvoiceNrSetting;

import eu.adampacholski.miniOffice.invoice.invoiceStatus.InvoiceStatus;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse.ItemWarehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/invoice_nr_setting")
public class InvoiceNrSettingController {
    private final InvoiceNrSettingService invoiceNrSettingService;

    @Autowired
    public InvoiceNrSettingController(InvoiceNrSettingService invoiceNrSettingService) {
        this.invoiceNrSettingService = invoiceNrSettingService;
    }

    @GetMapping("/get")
    public ResponseEntity<InvoiceNrSetting> get() {
        InvoiceNrSetting list = invoiceNrSettingService.getById(1L);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping(path = "/edit")
    public ResponseEntity<InvoiceNrSetting> edit(
            @RequestBody InvoiceNrSetting item
    ) {
        InvoiceNrSetting editItem = invoiceNrSettingService.update(item);
        return new ResponseEntity<>(editItem, HttpStatus.OK);
    }

}
