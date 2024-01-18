package eu.adampacholski.miniOffice.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getCustomers(){
        List<Customer> customers = customerService.getCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping("/add/{countryId}")
    public ResponseEntity<Customer> addCustomer(
            @PathVariable("countryId") Long countryId,
            @RequestBody Customer customer){
        Customer newCustomer = customerService.addCustomer(customer, countryId);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PutMapping(path = "/edit/{customerId}/{countryId}")
    public ResponseEntity<Customer> editCountry(
            @PathVariable("customerId") Long customerId,
            @PathVariable("countryId") Long countryId,
            @RequestBody Customer customer
    ){
        Customer editCountry = customerService.updateCustomer(countryId, customer, customerId);
        return new ResponseEntity<>(editCountry, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteCountry(
            @PathVariable("id") Long id
    ){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
