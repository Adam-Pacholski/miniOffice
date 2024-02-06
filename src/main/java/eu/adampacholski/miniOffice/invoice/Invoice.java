package eu.adampacholski.miniOffice.invoice;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import eu.adampacholski.miniOffice.customer.Customer;
import eu.adampacholski.miniOffice.invoice.invoiceStatus.InvoiceStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Invoice")
@Table(name = "invoice", uniqueConstraints = {
        @UniqueConstraint(name = "invoice_number_unique", columnNames = "invoice_number")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Invoice {

    @Id
    @SequenceGenerator(
            name = "invoice_sec",
            sequenceName = "invoice_sec",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "invoice_sec"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "invoice_number",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String invoiceNumber;

    @Column(
            name = "rised_date",
            nullable = false
    )
    private LocalDate risedDate;
    @Column(
            name = "termin_date",
            nullable = false
    )
    private LocalDate terminDate;
    @Column(
            name = "paid_date"
    )
    private LocalDate paidDate;
    @Column(
            name = "comments",
            columnDefinition = "TEXT"
    )
    private String comments;
    @Column(
            name = "tax",
            nullable = false
    )
    private Integer tax;

    @Column(
            name = "discount"
    )
    private Integer discount;


    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "customer_id_fk"),
            nullable = false
    )
//    @JsonBackReference
    private Customer customer;

    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "invoice_status_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "invoice_status_id_fr"),
            nullable = false
    )
    private InvoiceStatus invoiceStatus;

    public Invoice() {
    }

    public Invoice(String invoiceNumber, String comments, Integer tax, Integer discount, Customer customer, InvoiceStatus invoiceStatus) {
        this.invoiceNumber = invoiceNumber;
        this.comments = comments;
        this.tax = tax;
        this.discount = discount;
        this.customer = customer;
        this.invoiceStatus = invoiceStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getRisedDate() {
        return risedDate;
    }

    public void setRisedDate(LocalDate risedDate) {
        this.risedDate = risedDate;
    }

    public LocalDate getTerminDate() {
        return terminDate;
    }

    public void setTerminDate(LocalDate terminDate) {
        this.terminDate = terminDate;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", risedDate=" + risedDate +
                ", terminDate=" + terminDate +
                ", paidDate=" + paidDate +
                ", comments='" + comments + '\'' +
                ", tax=" + tax +
                ", discount=" + discount +
                ", customer=" + customer +
                ", invoiceStatus=" + invoiceStatus +
                '}';
    }
}
