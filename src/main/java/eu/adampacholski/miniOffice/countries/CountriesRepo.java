package eu.adampacholski.miniOffice.countries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountriesRepo extends JpaRepository<Countries, Long> {
    @Query("SELECT s FROM Countries s WHERE s.id = ?1")
    Optional<Countries> findCountriesById(Long id);

    @Query("SELECT s FROM Countries s WHERE s.countryName = ?1")
    Optional<Countries> findCountriesByCountryName(String countryName);

    @Query("SELECT s FROM Countries s WHERE s.code = ?1")
    Optional<Countries> findCountriesByCode(Integer code);
}
