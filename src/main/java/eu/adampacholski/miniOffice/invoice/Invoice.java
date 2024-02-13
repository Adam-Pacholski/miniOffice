package eu.adampacholski.miniOffice.invoice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import eu.adampacholski.miniOffice.customer.Customer;
import eu.adampacholski.miniOffice.invoice.invoiceStatus.InvoiceStatus;
import eu.adampacholski.miniOffice.invoice.invoiceType.InvoiceType;
import eu.adampacholski.miniOffice.invoice.productList.ProductList;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Invoice")
@Table(name = "invoice", uniqueConstraints = {
        @UniqueConstraint(name = "invoice_number_unique", columnNames = "invoice_number")
}
)
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
            name = "id"
//            updatable = false
    )
    private Long id;

    @Column(
            name = "invoice_number",
            columnDefinition = "TEXT"
    )
    private String invoiceNumber;

    @Column(
            name = "rised_date",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime risedDate;
    @Column(
            name = "termin_date",
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime terminDate;
    @Column(
            name = "paid_date",
            columnDefinition = "TIMESTAMP"
    )
    private LocalDate paidDate;
    @Column(
            name = "comments",
            columnDefinition = "TEXT"
    )
    private String comments;

    @Column(
            name = "discount"
    )
    private Integer discount;

    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch =  FetchType.EAGER
    )
    @JoinColumn(
            name = "invoice_type_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "invoice_type_id_fk")
//            nullable = false
    )

    private InvoiceType invoiceType;

    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "customer_id_fk")
//            nullable = false
    )
    private Customer customer;

    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "invoice_status_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "invoice_status_id_fr")
//            nullable = false
    )

    private InvoiceStatus invoiceStatus;

    @OneToMany(
//            mappedBy = "invoice",
            cascade = CascadeType.PERSIST,
//            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "invoice_id",
            referencedColumnName = "id"
    )

    private List<ProductList> productLists;

    public Invoice() {
    }


    public Invoice(String invoiceNumber, LocalDateTime risedDate, LocalDateTime terminDate, LocalDate paidDate, String comments, Integer discount, InvoiceType invoiceType, Customer customer, InvoiceStatus invoiceStatus, List<ProductList> productLists) {
        this.invoiceNumber = invoiceNumber;
        this.risedDate = risedDate;
        this.terminDate = terminDate;
        this.paidDate = paidDate;
        this.comments = comments;
        this.discount = discount;
        this.invoiceType = invoiceType;
        this.customer = customer;
        this.invoiceStatus = invoiceStatus;
        this.productLists = productLists;
    }

    public List<ProductList> getProductLists() {
        return productLists;
    }

    public void setProductLists(List<ProductList> productLists) {
        this.productLists = productLists;
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

    public LocalDateTime getRisedDate() {
        return risedDate;
    }

    public void setRisedDate(LocalDateTime risedDate) {
        this.risedDate = risedDate;
    }

    public LocalDateTime getTerminDate() {
        return terminDate;
    }

    public void setTerminDate(LocalDateTime terminDate) {
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

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
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
                ", discount=" + discount +
                ", invoiceType=" + invoiceType +
                ", customer=" + customer +
                ", invoiceStatus=" + invoiceStatus +
                ", productLists=" + productLists +
                '}';
    }
}
