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

    public List<Customer> getAllByType(String name){return customerRepo.findAllByType(name).get();}

    public List<Customer> getAllByNotType(String name){return customerRepo.findAllByNotType(name).get();}

    public Customer addCustomer(Customer customer){
        Optional<Customer> customerByName = customerRepo.findCustomerByName(customer.getName());
        if(customerByName.isPresent())
            throw new NotFoundException("Podana nazwa już istnieje");
       return customerRepo.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customer){
        Customer oldCustomer = customerRepo.findById(id).get();
        Optional<Customer> customerByName = customerRepo.findCustomerByNameAndNotId(customer.getName(), id);
        if(customerByName.isPresent() )
            throw new NotFoundException("Podana nazwa już istnieje");

        oldCustomer.setName(customer.getName());
        oldCustomer.setStreet(customer.getStreet());
        oldCustomer.setPostCode(customer.getPostCode());
        oldCustomer.setCity(customer.getCity());
        oldCustomer.setPhone(customer.getPhone());
        oldCustomer.setPhone(customer.getPhone());
        oldCustomer.setCountries(customer.getCountries());

        return customerRepo.save(oldCustomer);
    }

    public void deleteCustomer(Long id){
        boolean exist = customerRepo.existsById(id);
        if(!exist)
            throw new NotFoundException("W bazie nie ma klienta o id: "+id);
        customerRepo.deleteById(id);
    }
}
