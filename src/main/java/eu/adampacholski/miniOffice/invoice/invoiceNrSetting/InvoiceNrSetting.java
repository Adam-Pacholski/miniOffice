package eu.adampacholski.miniOffice.invoice.invoiceNrSetting;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "InvoiceNrSetting")
@Table(name = "invoice_nr_setting")
public class InvoiceNrSetting {

    @Id
    @SequenceGenerator(
            name = "invoice_nr_setting_sec",
            sequenceName = "invoice_nr_setting_sec",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "invoice_nr_setting_sec"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(name = "year",
            nullable = false)
    private Integer year;
    @Column(name = "number",
            nullable = false)
    private Integer number;



    public InvoiceNrSetting() {
    }

    public InvoiceNrSetting(Integer year, Integer number) {
        this.year = year;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "InvoiceNrSetting{" +
                "id=" + id +
                ", year=" + year +
                ", number=" + number +
                '}';
    }
}
