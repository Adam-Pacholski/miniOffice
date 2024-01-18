package eu.adampacholski.miniOffice;

import eu.adampacholski.miniOffice.countries.Countries;
import eu.adampacholski.miniOffice.countries.CountriesRepo;
import eu.adampacholski.miniOffice.customer.Customer;
import eu.adampacholski.miniOffice.customer.CustomerRepo;
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
	CommandLineRunner commandLineRunner(CountriesRepo countriesRepo, CustomerRepo customerRepo){
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
			Customer adam = new Customer("Adam Pacholski", "Am Bahndamm 10", "26197", "Huntlosen", "adam.pacholski1985@gmail.com", "1783466541", "Prywatna");
			Customer anna = new Customer("Anna Smerechynska", "11 Listopada 42/8", "03447", "Warszawa", "anna.smerechynska@gmail.com", "123456789", "Prywatna");
			Customer malina = new Customer("Usługi graficzne 'BEZSENS'", "Wyimaginowana ulica 5", "00001", "Nibylandia", "info@bezsens.com", "666333666999", "Firma");
			adam.setCountries(countriesRepo.findCountriesById(2L).get());
			anna.setCountries(countriesRepo.findCountriesById(1L).get());
			malina.setCountries(countriesRepo.findCountriesById(3L).get());
			customerRepo.save(adam);
			customerRepo.save(anna);
			customerRepo.save(malina);

		};
	}
}
