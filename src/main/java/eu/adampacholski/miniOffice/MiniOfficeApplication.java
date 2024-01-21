package eu.adampacholski.miniOffice;

import eu.adampacholski.miniOffice.countries.Countries;
import eu.adampacholski.miniOffice.countries.CountriesRepo;
import eu.adampacholski.miniOffice.customer.Customer;
import eu.adampacholski.miniOffice.customer.CustomerRepo;
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
										ItemRepo itemRepo){
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
			adam.setCountries(countriesRepo.findCountriesById(2L).get());
			anna.setCountries(countriesRepo.findCountriesById(1L).get());
			malina.setCountries(countriesRepo.findCountriesById(3L).get());
			customerRepo.save(adam);
			customerRepo.save(anna);
			customerRepo.save(malina);

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

			Item item1 = new Item("02158965555", "Wkręty 4x35", "Wkręty o długości 35mm do drewna", 58.12);
			Item item2 = new Item("021566465555", "Wkręty 4x40", "Wkręty o długości 40mm do drewna", 59.12);
			Item item3 = new Item("11166465555", "Płyta 2800x2070 Biała", "Płyta wiurowa oklejana białą okleiną", 80.99);

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

		};
	}
}
