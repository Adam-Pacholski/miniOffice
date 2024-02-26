package eu.adampacholski.miniOffice;

import eu.adampacholski.miniOffice.countries.Countries;
import eu.adampacholski.miniOffice.countries.CountriesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Install implements CommandLineRunner {

    private final CountriesService countriesService;

    public Install(CountriesService countriesService) {
        this.countriesService = countriesService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (args.length !=0){
            countriesService.addCountry(new Countries(48, "Polska"));
        }
    }
}
