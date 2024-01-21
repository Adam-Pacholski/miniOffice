package eu.adampacholski.miniOffice.item.itemWarehouse.itemUnit;

import eu.adampacholski.miniOffice.Exception.NotFoundException;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse.ItemWarehouse;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse.ItemWarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemUnitService {
    private final ItemUnitRepo itemUnitRepo;
@Autowired
    public ItemUnitService(ItemUnitRepo itemUnitRepo) {
        this.itemUnitRepo = itemUnitRepo;
    }

    public List<ItemUnit> getItems() {
        return itemUnitRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public ItemUnit getItemById(Long id){return itemUnitRepo.findById(id).get();}


    public ItemUnit addItem(ItemUnit itemUnit) {
        Optional<ItemUnit> _item = itemUnitRepo.findItemByName(itemUnit.getName());
        if (_item.isPresent())
            throw new NotFoundException("Podana nazwa już istnieje");
        return itemUnitRepo.save(itemUnit);
    }

    public ItemUnit updateItem(ItemUnit itemUnit, Long id) {
        ItemUnit newItem = itemUnitRepo.findById(id).get();
        Optional<ItemUnit> _item = itemUnitRepo.findItemByName(itemUnit.getName());
        if (_item.isPresent())
            throw new NotFoundException("Podana nazwa już istnieje");
        newItem.setName(itemUnit.getName());
        return itemUnitRepo.save(newItem);
    }

    public void deleteItem(Long id) {
        boolean exist = itemUnitRepo.existsById(id);
        if (!exist)
            throw new NotFoundException("Nie znaleziono...");
        itemUnitRepo.deleteById(id);
    }
}
