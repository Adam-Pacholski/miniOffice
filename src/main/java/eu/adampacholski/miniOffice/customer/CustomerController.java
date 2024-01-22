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
    public ResponseEntity<List<Customer>> get(){
        List<Customer> customers = customerService.getCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<Customer> getById(
            @PathVariable("id") Long id
    ){
        Customer customers = customerService.getCustomerById(id);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/allByType/{name}")
    public ResponseEntity<List<Customer>> getByType(
            @PathVariable("name") String name
    ){
        List<Customer> customers = customerService.getAllByType(name);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/allByNotType/{name}")
    public ResponseEntity<List<Customer>> getByNotType(
            @PathVariable("name") String name
    ){
        List<Customer> customers = customerService.getAllByNotType(name);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> add(
            @RequestBody Customer customer){
        Customer newCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PutMapping(path = "/edit/{id}")
    public ResponseEntity<Customer> edit(
            @PathVariable("id") Long id,
            @RequestBody Customer customer
    ){
        Customer editCustomer = customerService.updateCustomer(id,customer);
        return new ResponseEntity<>(editCustomer, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Long id
    ){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
