package eu.adampacholski.miniOffice.invoice.invoiceStatus;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "InvoiceStatus")
@Table(name = "invoice_status", uniqueConstraints = {
        @UniqueConstraint(name = "invoice_status_unique", columnNames = "stat")
})
public class InvoiceStatus {
    @Id
    @SequenceGenerator(
            name = "invoice_status_sec",
            sequenceName = "invoice_status_sec",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "invoice_status_sec"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(name = "stat")
    private String stat;

    public InvoiceStatus() {
    }

    public InvoiceStatus(String stat) {
        this.stat = stat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        return "InvoiceStatus{" +
                "id=" + id +
                ", stat='" + stat + '\'' +
                '}';
    }

}
