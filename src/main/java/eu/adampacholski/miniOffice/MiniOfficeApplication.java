package eu.adampacholski.miniOffice;

import eu.adampacholski.miniOffice.countries.Countries;
import eu.adampacholski.miniOffice.countries.CountriesRepo;
import eu.adampacholski.miniOffice.customer.Customer;
import eu.adampacholski.miniOffice.customer.CustomerRepo;
import eu.adampacholski.miniOffice.invoice.Invoice;
import eu.adampacholski.miniOffice.invoice.invoiceNrSetting.InvoiceNrSetting;
import eu.adampacholski.miniOffice.invoice.invoiceNrSetting.InvoiceNrSettingRepo;
import eu.adampacholski.miniOffice.invoice.InvoiceRepo;
import eu.adampacholski.miniOffice.invoice.invoiceStatus.InvoiceStatus;
import eu.adampacholski.miniOffice.invoice.invoiceStatus.InvoiceStatusRepo;
import eu.adampacholski.miniOffice.invoice.invoiceType.InvoiceType;
import eu.adampacholski.miniOffice.invoice.invoiceType.InvoiceTypeRepo;
import eu.adampacholski.miniOffice.item.itemWarehouse.Item;
import eu.adampacholski.miniOffice.item.itemWarehouse.ItemRepo;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemCategory.ItemCategory;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemCategory.ItemCategoryRepo;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemUnit.ItemUnit;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemUnit.ItemUnitRepo;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse.ItemWarehouse;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse.ItemWarehouseRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication

public class MiniOfficeApplication {

	public static void main(String[] args) {

		SpringApplication.run(MiniOfficeApplication.class, args);
	}
}
