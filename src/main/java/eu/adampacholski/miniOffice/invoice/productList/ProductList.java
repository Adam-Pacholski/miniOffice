package eu.adampacholski.miniOffice.invoice.productList;

import eu.adampacholski.miniOffice.customer.Customer;
import eu.adampacholski.miniOffice.invoice.Invoice;
import eu.adampacholski.miniOffice.item.itemWarehouse.Item;
import jakarta.persistence.*;

@Entity(name = "ProductList")
@Table(name = "product_list")
public class ProductList {
    @Id
    @SequenceGenerator(
            name = "product_list_sec",
            sequenceName = "product_list_sec",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_list_sec"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "tax",
            nullable = false
    )
    private Integer tax; // podatek
    @Column(
            name = "amount",
            nullable = false
    )
    private Integer amount; // ilość
    @Column(
            name = "margin",
            nullable = false
    )
    private Integer margin; // marża

    @ManyToOne
    private Invoice invoice;

    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "item_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "item_prodList_id_fk")
//            nullable = false
    )
    private Item item;

    public ProductList() {
    }

    public ProductList(Integer tax, Integer amount, Integer margin, Item item, Invoice invoice) {
        this.tax = tax;
        this.amount = amount;
        this.margin = margin;
        this.invoice = invoice;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "id=" + id +
                ", tax=" + tax +
                ", amount=" + amount +
                ", margin=" + margin +
                ", invoice=" + invoice +
                ", item=" + item +
                '}';
    }
}
