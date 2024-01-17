package eu.adampacholski.miniOffice.countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/countries")
public class CountriesController {
    private final CountriesService countriesService;

    @Autowired
    public CountriesController(CountriesService countriesService) {
        this.countriesService = countriesService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Countries>> getCountries(){
        List<Countries> countries = countriesService.getAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Countries> addCountry(
            @RequestBody Countries country){
        Countries newCountry = countriesService.addCountry(country);
        return new ResponseEntity<>(newCountry, HttpStatus.CREATED);
    }

    @PutMapping(path = "/edit/{id}")
    public ResponseEntity<Countries> editCountry(
            @PathVariable("id") Long id,
            @RequestBody Countries countries
    ){
        Countries editCountry = countriesService.updateCountryById(id, countries);
        return new ResponseEntity<>(editCountry, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteCountry(
            @PathVariable("id") Long id
    ){
        countriesService.deleteCountry(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
