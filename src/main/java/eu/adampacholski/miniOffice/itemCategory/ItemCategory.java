package eu.adampacholski.miniOffice.itemCategory;

import jakarta.persistence.*;

@Entity(name = "ItemCategory")
@Table(name = "item_category", uniqueConstraints = {
        @UniqueConstraint(name = "item_name_uniq", columnNames = "name")
})
public class ItemCategory {
    @Id
    @SequenceGenerator(
            name="item_category_sec",
            sequenceName = "item_category_sec",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_category_sec"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    public ItemCategory() {
    }

    public ItemCategory(String name) {
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
//test
    @Override
    public String toString() {
        return "ItemCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
