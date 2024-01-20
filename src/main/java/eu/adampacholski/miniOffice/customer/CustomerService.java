package eu.adampacholski.miniOffice.customer;

import eu.adampacholski.miniOffice.Exception.NotFoundException;
import eu.adampacholski.miniOffice.countries.CountriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Customer> getCustomers(){
        return customerRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Customer getCustomerById(Long id){return customerRepo.findById(id).get();}
    public Customer addCustomer(Customer customer, Long countryId){
        Optional<Customer> customerByName = customerRepo.findCustomerByName(customer.getName());
        if(customerByName.isPresent())
            throw new NotFoundException("Podana nazwa już istnieje");
        customer.setCountries(countriesRepo.findCountriesById(countryId).get());
       return customerRepo.save(customer);
    }

    public Customer updateCustomer(Long customerId , Long countryId, Customer customer){
        Customer oldCustomer = customerRepo.findById(customerId).get();
        Optional<Customer> customerByName = customerRepo.findCustomerByName(customer.getName());
        if(customerByName.isPresent())
            throw new NotFoundException("Podana nazwa już istnieje");

        oldCustomer.setName(customer.getName());
        oldCustomer.setStreet(customer.getStreet());
        oldCustomer.setPostCode(customer.getPostCode());
        oldCustomer.setCity(customer.getCity());
        oldCustomer.setPhone(customer.getPhone());
        oldCustomer.setPhone(customer.getPhone());
        oldCustomer.setCountries(countriesRepo.findCountriesById(countryId).get());

        return customerRepo.save(oldCustomer);
    }

    public void deleteCustomer(Long id){
        boolean exist = customerRepo.existsById(id);
        if(!exist)
            throw new NotFoundException("W bazie nie ma klienta o id: "+id);
        customerRepo.deleteById(id);
    }
}
