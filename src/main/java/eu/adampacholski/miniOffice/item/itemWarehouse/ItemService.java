package eu.adampacholski.miniOffice.item.itemWarehouse;

import eu.adampacholski.miniOffice.Exception.NotFoundException;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse.ItemWarehouse;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse.ItemWarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepo itemRepo;

    @Autowired
    public ItemService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public List<Item> getItems() {
        return itemRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Item getItemById(Long id) {
        return itemRepo.findById(id).get();
    }


    public Item addItem(Item item) {
        Optional<Item> _item = itemRepo.findItemByPartName(item.getPartNumber());
        if (_item.isPresent())
            throw new NotFoundException("Podana część już istnieje");
        return itemRepo.save(item);
    }

    public Item updateItem(Item item, Long id) {
        Item newItem = itemRepo.findById(id).get();
        Optional<Item> _item = itemRepo.findItemByPartName(item.getPartNumber());
        if (_item.isPresent())
            throw new NotFoundException("Podana nazwa już istnieje");
        newItem.setPartNumber(item.getPartNumber());
        newItem.setName(item.getName());
        newItem.setDescription(item.getDescription());
        newItem.setPrice(item.getPrice());
        newItem.setItemCategory(item.getItemCategory());
        newItem.setItemWarehouse(item.getItemWarehouse());
        newItem.setItemUnit(item.getItemUnit());

        return itemRepo.save(newItem);
    }

    public void deleteItem(Long id) {
        boolean exist = itemRepo.existsById(id);
        if (!exist)
            throw new NotFoundException("W bazie nie ma kategorii o id:" + id);
        itemRepo.deleteById(id);
    }
}
