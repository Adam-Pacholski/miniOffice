package eu.adampacholski.miniOffice.customer;

import eu.adampacholski.miniOffice.countries.Countries;
import jakarta.persistence.*;

@Entity(name = "Customer")
@Table(name = "customer", uniqueConstraints = {
        @UniqueConstraint(name = "customer_name_uniq", columnNames = "name")
})

public class Customer {
    @Id
    @SequenceGenerator(
            name="customer_sec",
            sequenceName = "customer_sec",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sec"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
            name = "street",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String street;
    @Column(
            name = "post_code",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String postCode;
    @Column(
            name = "city",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String city;
    @Column(
            name = "email",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String email;
    @Column(
            name = "phone",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String phone;
    @Column(name = "customer_type",
    nullable = false,
            columnDefinition = "TEXT")
    private String customerType;


    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "country_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "country_id_fk"),
            nullable = false
    )
    private Countries countries;


    public Customer() {
    }
    public Customer(String name, String street, String postCode, String city, String email, String phone, String customerType) {

        this.name = name;
        this.street = street;
        this.postCode = postCode;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.customerType = customerType;
    }

    public Customer(String name, String street, String postCode, String city, String email, String phone, String customerType, Countries countries) {
        this.name = name;
        this.street = street;
        this.postCode = postCode;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.customerType = customerType;
        this.countries = countries;
    }

    public Countries getCountries() {
        return countries;
    }

    public void setCountries(Countries countries) {
        this.countries = countries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", customerType='" + customerType + '\'' +
                ", countries=" + countries +
                '}';
    }
}
