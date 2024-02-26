package eu.adampacholski.miniOffice.invoice;


import eu.adampacholski.miniOffice.invoice.invoiceNrSetting.InvoiceNrSetting;
import eu.adampacholski.miniOffice.invoice.invoiceNrSetting.InvoiceNrSettingRepo;
import eu.adampacholski.miniOffice.invoice.invoiceType.InvoiceTypeRepo;
import eu.adampacholski.miniOffice.invoice.productList.ProductList;
import eu.adampacholski.miniOffice.invoice.productList.ProductListRepo;
import eu.adampacholski.miniOffice.item.itemWarehouse.Item;
import eu.adampacholski.miniOffice.item.itemWarehouse.ItemService;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse.ItemWarehouseRepo;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse.ItemWarehouseService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepo invoiceRepo;
    private final InvoiceNrSettingRepo invoiceNrSettingRepo;
    private final ItemWarehouseService itemWarehouseService;
    private final ItemService itemService;
    private final ProductListRepo productListRepo;

    public InvoiceService(InvoiceRepo invoiceRepo, InvoiceNrSettingRepo invoiceNrSettingRepo, ItemWarehouseService itemWarehouseService, ItemService itemService, ProductListRepo productListRepo) {
        this.invoiceRepo = invoiceRepo;
        this.invoiceNrSettingRepo = invoiceNrSettingRepo;
        this.itemWarehouseService = itemWarehouseService;
        this.itemService = itemService;

        this.productListRepo = productListRepo;
    }
    public List<Invoice> get() {
        return invoiceRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public List<Invoice> getAllByCustomerId(Long id){
        return invoiceRepo.findAllByCommentsId(id, Sort.by(Sort.Direction.DESC, "id"));
    }
    public Invoice add(Invoice item, Integer days) {

        LocalDateTime date = LocalDateTime.now();
        InvoiceNrSetting setting = null;
        Long typ= item.getInvoiceType().getId();
        if (typ == 1){
          setting  = invoiceNrSettingRepo.findById(1L).get();
        }
        if (typ == 2){
            setting  = invoiceNrSettingRepo.findById(2L).get();
        }
        if (typ == 3){
            setting  = invoiceNrSettingRepo.findById(3L).get();
        }
        if (typ == 4){
            setting  = invoiceNrSettingRepo.findById(4L).get();
        }

        item.setRisedDate(date);
        item.setTerminDate(date.plusDays(days));
        if (item.getInvoiceStatus().getId() == 1)
            item.setPaidDate(LocalDate.now());
        if (setting.getYear() < date.getYear()) {
            setting.setYear(date.getYear());
            setting.setNumber(1);
            invoiceNrSettingRepo.save(setting);
        }

        String invNumber = item.getInvoiceType().getCode() + "/" + setting.getNumber() + "/" + setting.getYear();
        item.setInvoiceNumber(invNumber);
        setting.setNumber(setting.getNumber()+1);


        for (int i = 0; i < item.getProductLists().size() ; i++) {
            Item newItem = itemService.getById(item.getProductLists().get(i).getItem().getId());
            newItem.setAmount(newItem.getAmount()-item.getProductLists().get(i).getAmount());
            itemService.update(newItem, newItem.getId());
        }
        return invoiceRepo.save(item);
    }
}
