package eu.adampacholski.miniOffice.item.itemWarehouse;

import eu.adampacholski.miniOffice.item.itemWarehouse.itemCategory.ItemCategory;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemUnit.ItemUnit;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse.ItemWarehouse;
import jakarta.persistence.*;

@Entity(name = "Item")
@Table(name = "item", uniqueConstraints = {
        @UniqueConstraint(name = "item_partNumber_uniq", columnNames = "part_number")
})
public class Item {
    @Id
    @SequenceGenerator(
            name = "item_sec",
            sequenceName = "item_sec",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sec"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(name = "part_number",
            nullable = false,
            columnDefinition = "TEXT")
    private String partNumber;
    @Column(
            name = "name",
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
            name = "description",
            columnDefinition = "TEXT"
    )
    private String description;
    @Column(name = "price",
            nullable = false)
    private Double price;
    @Column(name = "amount")
    private Integer amount;

    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "item_cat_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "item_cat_id_fk"),
            nullable = false
    )
    private ItemCategory itemCategory;
    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "item_wh_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "item_wh_id_fk"),
            nullable = false
    )
    private ItemWarehouse itemWarehouse;
    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "item_unit_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "item_unit_id_fk"),
            nullable = false
    )
    private ItemUnit itemUnit;

    public Item() {
    }

    public Item(String partNumber, String name, String description, Double price, Integer amount) {
        this.partNumber = partNumber;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public Item(String partNumber, String name, String description, Double price, Integer amount, ItemCategory itemCategory, ItemWarehouse itemWarehouse, ItemUnit itemUnit) {
        this.partNumber = partNumber;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.itemCategory = itemCategory;
        this.itemWarehouse = itemWarehouse;
        this.itemUnit = itemUnit;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ItemUnit getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(ItemUnit itemUnit) {
        this.itemUnit = itemUnit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public ItemWarehouse getItemWarehouse() {
        return itemWarehouse;
    }

    public void setItemWarehouse(ItemWarehouse itemWarehouse) {
        this.itemWarehouse = itemWarehouse;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", partNumber='" + partNumber + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", itemCategory=" + itemCategory +
                ", itemWarehouse=" + itemWarehouse +
                '}';
    }
}
