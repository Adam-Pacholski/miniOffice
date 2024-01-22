package eu.adampacholski.miniOffice.countries;

import eu.adampacholski.miniOffice.Exception.NotFoundException;
import eu.adampacholski.miniOffice.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CountriesService {

    private final CountriesRepo countriesRepo;

    @Autowired
    public CountriesService(CountriesRepo countriesRepo) {
        this.countriesRepo = countriesRepo;
    }

    public List<Countries> getAllCountries(){
        return countriesRepo.findAll(Sort.by(Sort.Direction.ASC,"countryName"));
    }
    public Countries getCountriesById(Long id){
        return countriesRepo.findById(id).get();
    }

    public Countries addCountry(Countries country){
        Optional<Countries> _country = countriesRepo.findCountriesByCountryName(country.getCountryName());
        if(_country.isPresent())
            throw new NotFoundException("Istnieje już w bazie podana nazwa kraju");
        _country = countriesRepo.findCountriesByCode(country.getCode());
        if (_country.isPresent()){
            throw new NotFoundException("Istnieje w bazie kraj o podanym numerze kierunkowym");
        }
        return countriesRepo.save(country);
    }

   public Countries updateCountryById(Long id, Countries countries){
        Optional<Countries> _coutry = countriesRepo.findCountriesByCountryNameNotId(countries.getCountryName(), id);
        if(_coutry.isPresent())
            throw new NotFoundException("Podany kraj już istnieje");
        _coutry = countriesRepo.findCountriesByCodeNotId(countries.getCode(),id);
        if (_coutry.isPresent())
            throw new NotFoundException("Podany numer kierunkowy już istnieje");

        Countries newCountry = countriesRepo.findCountriesById(id).get();
        newCountry.setCountryName(countries.getCountryName());
        newCountry.setCode(countries.getCode());

        return countriesRepo.save(newCountry);
   }

   public void deleteCountry(Long id){
        boolean exist = countriesRepo.existsById(id);
        if (!exist)
            throw new NotFoundException("W bazie danych nie ma kraju o id: " + id);
        countriesRepo.deleteById(id);
   }
}
