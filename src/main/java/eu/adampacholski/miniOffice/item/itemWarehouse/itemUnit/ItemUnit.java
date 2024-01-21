package eu.adampacholski.miniOffice.item.itemWarehouse.itemUnit;

import jakarta.persistence.*;

@Entity(name = "ItemUnit")
@Table(name = "item_unit", uniqueConstraints = {
        @UniqueConstraint(name = "item_unit_name_uniq", columnNames = "name")
})
public class ItemUnit
{

    @Id
    @SequenceGenerator(
            name = "item_unit_sec",
            sequenceName = "item_unit_sec",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_unit_sec"
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

    public ItemUnit() {
    }

    public ItemUnit(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "ItemUnit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
