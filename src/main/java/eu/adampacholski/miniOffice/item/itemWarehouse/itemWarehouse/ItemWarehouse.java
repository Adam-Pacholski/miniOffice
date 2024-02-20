package eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse;

import jakarta.persistence.*;

@Entity(name = "ItemWarehouse")
@Table(name = "item_warehouse", uniqueConstraints = {
        @UniqueConstraint(name = "item_warehouse_name_uniq", columnNames = "name")
})
public class ItemWarehouse {
    @Id
    @SequenceGenerator(
            name = "item_warehouse_sec",
            sequenceName = "item_warehouse_sec",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_warehouse_sec"
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
        name = "warehouse_stat"
)
    private Integer warehouseStat;

    public ItemWarehouse() {
    }

    public ItemWarehouse(String name, Integer warehouseStat) {
        this.name = name;
        this.warehouseStat = warehouseStat;
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

    public Integer getWarehouseStat() {
        return warehouseStat;
    }

    public void setWarehouseStat(Integer warehouseStat) {
        this.warehouseStat = warehouseStat;
    }

    @Override
    public String toString() {
        return "ItemWarehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", warehouseStat=" + warehouseStat +
                '}';
    }
}
