package eu.adampacholski.miniOffice.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query("SELECT s FROM Customer s WHERE s.name = ?1")
    Optional<Customer> findCustomerByName(String name);

    @Query("SELECT s FROM Customer s WHERE s.name = ?1 AND NOT s.id=?2")
    Optional<Customer> findCustomerByNameAndNotId(String name, Long id);


}
