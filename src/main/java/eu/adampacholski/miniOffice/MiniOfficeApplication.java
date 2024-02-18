package eu.adampacholski.miniOffice;

import eu.adampacholski.miniOffice.countries.Countries;
import eu.adampacholski.miniOffice.countries.CountriesRepo;
import eu.adampacholski.miniOffice.customer.Customer;
import eu.adampacholski.miniOffice.customer.CustomerRepo;
import eu.adampacholski.miniOffice.invoice.Invoice;
import eu.adampacholski.miniOffice.invoice.invoiceNrSetting.InvoiceNrSetting;
import eu.adampacholski.miniOffice.invoice.invoiceNrSetting.InvoiceNrSettingRepo;
import eu.adampacholski.miniOffice.invoice.InvoiceRepo;
import eu.adampacholski.miniOffice.invoice.invoiceNrSetting.InvoiceNrSettingService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication

public class MiniOfficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniOfficeApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CountriesRepo countriesRepo,
										CustomerRepo customerRepo,
										ItemCategoryRepo itemCategoryRepo,
										ItemWarehouseRepo itemWarehouseRepo,
										ItemUnitRepo itemUnitRepo,
										ItemRepo itemRepo,
										InvoiceStatusRepo invoiceStatusRepo,
										InvoiceNrSettingRepo invoiceNrSettingRepo,
										InvoiceRepo invoiceRepo,
										InvoiceTypeRepo invoiceTypeRepo){
		return args -> {

			//Dodanie 3 krajów
			Countries polska = new Countries(48, "Polska");
			Countries niemcy = new Countries(49, "Niemcy");
			Countries ukraina = new Countries(380, "Ukraina");
			Countries holandia = new Countries(31, "Holandia");
			countriesRepo.save(polska);
			countriesRepo.save(niemcy);
			countriesRepo.save(ukraina);
			countriesRepo.save(holandia);

			// Dodanie 3 Klientów
			Customer adam = new Customer("Adam Pacholski", "Am Bahndamm 10", "26197", "Huntlosen", "adam.pacholski1985@gmail.com", "1783466541", "Prywatny");
			Customer anna = new Customer("Anna Smerechynska", "11 Listopada 42/8", "03447", "Warszawa", "anna.smerechynska@gmail.com", "123456789", "Prywatny");
			Customer malina = new Customer("Usługi graficzne 'BEZSENS'", "Wyimaginowana ulica 5", "00001", "Nibylandia", "info@bezsens.com", "666333666999", "Firma");
			Customer ups = new Customer("Rogemann1", "Hogenbogen 18","46198","Visbek","rogeman@info.de","234-334", "Dostawca");
			Customer ups2 = new Customer("Rogemann2", "Hogenbogen 18","46198","Visbek","rogeman@info.de","234-334", "Prywatny");
			Customer ups3 = new Customer("Rogemann3", "Hogenbogen 18","46198","Visbek","rogeman@info.de","234-334", "Dostawca");
			Customer ups4 = new Customer("Rogemann4", "Hogenbogen 18","46198","Visbek","rogeman@info.de","234-334", "Prywatny");
			Customer ups5 = new Customer("Rogemann5", "Hogenbogen 18","46198","Visbek","rogeman@info.de","234-334", "Prywatny");
			Customer ups7 = new Customer("Rogemann7", "Hogenbogen 18","46198","Visbek","rogeman@info.de","234-334", "Prywatny");
			Customer ups8 = new Customer("Rogemann8", "Hogenbogen 18","46198","Visbek","rogeman@info.de","234-334", "Prywatny");
			Customer ups6 = new Customer("Rogemann6", "Hogenbogen 18","46198","Visbek","rogeman@info.de","234-334", "Prywatny");
			Customer ups9 = new Customer("Rogemann9", "Hogenbogen 18","46198","Visbek","rogeman@info.de","234-334", "Prywatny");
			Customer ups10 = new Customer("Rogemann10", "Hogenbogen 18","46198","Visbek","rogeman@info.de","234-334", "Prywatny");
			adam.setCountries(countriesRepo.findById(2L).get());
			anna.setCountries(countriesRepo.findById(1L).get());
			malina.setCountries(countriesRepo.findById(3L).get());
			ups.setCountries(countriesRepo.findById(2L).get());
			ups2.setCountries(countriesRepo.findById(2L).get());
			ups3.setCountries(countriesRepo.findById(2L).get());
			ups4.setCountries(countriesRepo.findById(2L).get());
			ups5.setCountries(countriesRepo.findById(2L).get());
			ups6.setCountries(countriesRepo.findById(2L).get());
			ups7.setCountries(countriesRepo.findById(2L).get());
			ups8.setCountries(countriesRepo.findById(2L).get());
			ups9.setCountries(countriesRepo.findById(2L).get());
			ups10.setCountries(countriesRepo.findById(2L).get());
			customerRepo.save(adam);
			customerRepo.save(anna);
			customerRepo.save(malina);
			customerRepo.save(ups);
			customerRepo.save(ups2);
			customerRepo.save(ups3);
			customerRepo.save(ups4);
			customerRepo.save(ups5);
			customerRepo.save(ups6);
			customerRepo.save(ups7);
			customerRepo.save(ups8);
			customerRepo.save(ups9);
			customerRepo.save(ups10);

			//Dodawanie 3 Item-category

			ItemCategory itemCategory1 = new ItemCategory("Płyty");
			ItemCategory itemCategory2 = new ItemCategory("Wkręty");
			ItemCategory itemCategory3 = new ItemCategory("Zawiasy");

			itemCategoryRepo.save(itemCategory1);
			itemCategoryRepo.save(itemCategory2);
			itemCategoryRepo.save(itemCategory3);

			//Dodawanie 3 Item-warehouse
			ItemWarehouse itemWarehouse1 = new ItemWarehouse("Warsztat");
			ItemWarehouse itemWarehouse2 = new ItemWarehouse("Płytownia");
			ItemWarehouse itemWarehouse3 = new ItemWarehouse("Półka z wkrętami");
			itemWarehouseRepo.save(itemWarehouse1);
			itemWarehouseRepo.save(itemWarehouse2);
			itemWarehouseRepo.save(itemWarehouse3);

			//Dodawanie 3 Item-unit
			ItemUnit itemUnit1 = new ItemUnit("Szt");
			ItemUnit itemUnit2 = new ItemUnit("Kg");
			ItemUnit itemUnit3 = new ItemUnit("Metr");
			itemUnitRepo.save(itemUnit1);
			itemUnitRepo.save(itemUnit2);
			itemUnitRepo.save(itemUnit3);

			Item item1 = new Item("02158965555", "Wkręty 4x35", "Wkręty o długości 35mm do drewna", 50.00,2);
			Item item2 = new Item("021566465555", "Wkręty 4x40", "Wkręty o długości 40mm do drewna", 59.12,10);
			Item item3 = new Item("11166465555", "Płyta 2800x2070 Biała", "Płyta wiurowa oklejana białą okleiną", 80.99,32);

			item1.setItemWarehouse(itemWarehouseRepo.findById(3L).get());
			item2.setItemWarehouse(itemWarehouseRepo.findById(3L).get());
			item3.setItemWarehouse(itemWarehouseRepo.findById(2L).get());

			item1.setItemCategory(itemCategoryRepo.findById(2L).get());
			item2.setItemCategory(itemCategoryRepo.findById(2L).get());
			item3.setItemCategory(itemCategoryRepo.findById(1L).get());

			item1.setItemUnit(itemUnitRepo.findById(1L).get());
			item2.setItemUnit(itemUnitRepo.findById(1L).get());
			item3.setItemUnit(itemUnitRepo.findById(1L).get());

			itemRepo.save(item1);
			itemRepo.save(item2);
			itemRepo.save(item3);


			InvoiceStatus inStat_1 = new InvoiceStatus("Zapłacone");
			InvoiceStatus inStat_2 = new InvoiceStatus("Niezapłacone");
			InvoiceStatus inStat_3 = new InvoiceStatus("Anulowane");
			InvoiceStatus inStat_4 = new InvoiceStatus("Korekta");

			invoiceStatusRepo.save(inStat_1);
			invoiceStatusRepo.save(inStat_2);
			invoiceStatusRepo.save(inStat_3);
			invoiceStatusRepo.save(inStat_4);

			InvoiceNrSetting invoiceNrSetting_1 = new InvoiceNrSetting(2023,4);
			invoiceNrSettingRepo.save(invoiceNrSetting_1);
			InvoiceNrSetting invoiceNrSetting_2 = new InvoiceNrSetting(2023,4);
			invoiceNrSettingRepo.save(invoiceNrSetting_2);
			InvoiceNrSetting invoiceNrSetting_3 = new InvoiceNrSetting(2023,4);
			invoiceNrSettingRepo.save(invoiceNrSetting_3);
			InvoiceNrSetting invoiceNrSetting_4 = new InvoiceNrSetting(2023,4);
			invoiceNrSettingRepo.save(invoiceNrSetting_4);

			InvoiceType in_type_1 = new InvoiceType("Faktura", "F");
			InvoiceType in_type_2 = new InvoiceType("Korekta", "K");
			InvoiceType in_type_3 = new InvoiceType("List przewozowy", "L");
			InvoiceType in_type_4 = new InvoiceType("Oferta", "O");
			invoiceTypeRepo.save(in_type_1);
			invoiceTypeRepo.save(in_type_2);
			invoiceTypeRepo.save(in_type_3);
			invoiceTypeRepo.save(in_type_4);

			LocalDateTime data = LocalDateTime.now();


			Invoice in_1 = new Invoice();
			in_1.setInvoiceNumber("F/01/2024");
			in_1.setCustomer(customerRepo.findById(2L).get());
			in_1.setInvoiceStatus(invoiceStatusRepo.findById(2L).get());
			in_1.setInvoiceType(invoiceTypeRepo.findById(1L).get());
			in_1.setRisedDate(data);
			in_1.setTerminDate(data.plusDays(14));

			invoiceRepo.save(in_1);

			Invoice in_2 = new Invoice();
			in_2.setInvoiceNumber("F/02/2024");
			in_2.setCustomer(customerRepo.findById(2L).get());
			in_2.setInvoiceStatus(invoiceStatusRepo.findById(1L).get());
			in_2.setInvoiceType(invoiceTypeRepo.findById(1L).get());
			in_2.setRisedDate(data);
			in_2.setTerminDate(data.plusDays(7));

			invoiceRepo.save(in_2);

		};
	}
}
