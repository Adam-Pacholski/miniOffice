package eu.adampacholski.miniOffice.countries;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Countries")
@Table(name = "countries", uniqueConstraints = {
        @UniqueConstraint(name = "country_name_unique", columnNames = "country_name")
})
public class Countries {

    @Id
    @SequenceGenerator(
            name = "countries_sec",
            sequenceName = "countries_sec",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "countries_sec"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "code",
            unique = true,
            nullable = false
    )
    private Integer code;
    @Column(
            name = "country_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String countryName;


    public Countries() {

    }

    public Countries(Integer code, String countryName) {
        this.code = code;
        this.countryName = countryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Countries{" +
                "id=" + id +
                ", code=" + code +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
