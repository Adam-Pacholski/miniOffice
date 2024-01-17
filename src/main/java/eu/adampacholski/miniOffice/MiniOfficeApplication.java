package eu.adampacholski.miniOffice;

import eu.adampacholski.miniOffice.countries.Countries;
import eu.adampacholski.miniOffice.countries.CountriesRepo;
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
	CommandLineRunner commandLineRunner(CountriesRepo countriesRepo){
		return args -> {
			Countries polska = new Countries(48, "Polska");
			Countries niemcy = new Countries(49, "Niemcy");
			Countries ukraina = new Countries(380, "Ukraina");
			Countries holandia = new Countries(31, "Holandia");
			countriesRepo.save(polska);
			countriesRepo.save(niemcy);
			countriesRepo.save(ukraina);
			countriesRepo.save(holandia);
		};
	}
}
