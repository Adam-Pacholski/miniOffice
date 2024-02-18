package eu.adampacholski.miniOffice.invoice.productList;

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
            name = "tax"
    )
    private Integer tax; // podatek
    @Column(
            name = "amount"
    )
    private Integer amount; // ilość
    @Column(
            name = "margin"
    )
    private Integer margin; // marża
    @Column(name = "price_netto")
    private Double priceNetto;
    @Column(name = "sum_netto")
    private Double sumNetto;
    @Column(name = "sum_brutto")
    private Double sumBrutto;
    @Column(
            name = "description",
            columnDefinition = "TEXT"
    )
    private String description; //opis

    @Column(
            name = "discount"
    )
    private Integer discount;
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

    public ProductList(Integer tax, Integer amount, Integer margin, Double priceNetto, Double sumNetto, Double sumBrutto, String description, Integer discount, Invoice invoice, Item item) {
        this.tax = tax;
        this.amount = amount;
        this.margin = margin;
        this.priceNetto = priceNetto;
        this.sumNetto = sumNetto;
        this.sumBrutto = sumBrutto;
        this.description = description;
        this.discount = discount;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPriceNetto() {
        return priceNetto;
    }

    public void setPriceNetto(Double priceNetto) {
        this.priceNetto = priceNetto;
    }

    public Double getSumNetto() {
        return sumNetto;
    }

    public void setSumNetto(Double sumNetto) {
        this.sumNetto = sumNetto;
    }

    public Double getSumBrutto() {
        return sumBrutto;
    }

    public void setSumBrutto(Double sumBrutto) {
        this.sumBrutto = sumBrutto;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "id=" + id +
                ", tax=" + tax +
                ", amount=" + amount +
                ", margin=" + margin +
                ", priceNetto=" + priceNetto +
                ", sumNetto=" + sumNetto +
                ", sumBrutto=" + sumBrutto +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                ", invoice=" + invoice +
                ", item=" + item +
                '}';
    }
}
