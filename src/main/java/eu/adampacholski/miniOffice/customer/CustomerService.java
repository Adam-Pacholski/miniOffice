package eu.adampacholski.miniOffice.customer;

import eu.adampacholski.miniOffice.Exception.NotFoundException;
import eu.adampacholski.miniOffice.countries.Countries;
import eu.adampacholski.miniOffice.countries.CountriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CountriesRepo countriesRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo, CountriesRepo countriesRepo) {
        this.customerRepo = customerRepo;
        this.countriesRepo = countriesRepo;
    }

    public Customer addCustomer(Long countryId, Customer customer){
        Optional<Customer> customerByName = customerRepo.findCustomerByName(customer.getName());
        if(customerByName.isPresent())
            throw new NotFoundException("Podana nazwa już istnieje");
        customer.setCountries(countriesRepo.findCountriesById(countryId).get());
       return customerRepo.save(customer);
    }
}
