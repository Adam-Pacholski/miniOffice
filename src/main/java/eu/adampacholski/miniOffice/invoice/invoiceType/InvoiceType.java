package eu.adampacholski.miniOffice.invoice.invoiceType;

import jakarta.persistence.*;

@Entity(name = "InvoiceType")
@Table(name = "invoice_type")
public class InvoiceType {
    @Id
    @SequenceGenerator(
            name = "invoice_type_sec",
            sequenceName = "invoice_type_sec",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "invoice_type_sec"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "in_type",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String type;
    @Column(
            name = "code",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String code;

    public InvoiceType() {
    }

    public InvoiceType(String type, String code) {
        this.type = type;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "InvoiceType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
