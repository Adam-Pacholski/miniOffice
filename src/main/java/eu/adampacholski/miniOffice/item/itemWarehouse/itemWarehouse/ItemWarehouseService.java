package eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse;

import eu.adampacholski.miniOffice.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemWarehouseService {
    private final ItemWarehouseRepo itemWarehouseRepo;

    @Autowired
    public ItemWarehouseService(ItemWarehouseRepo itemWarehouseRepo) {
        this.itemWarehouseRepo = itemWarehouseRepo;
    }

    public List<ItemWarehouse> getItems() {
        return itemWarehouseRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public ItemWarehouse getItemById(Long id){return itemWarehouseRepo.findById(id).get();}


    public ItemWarehouse addItem(ItemWarehouse itemWarehouse) {
        Optional<ItemWarehouse> _item = itemWarehouseRepo.findItemWarehouseByName(itemWarehouse.getName());
        if (_item.isPresent())
            throw new NotFoundException("Podana nazwa już istnieje");
        return itemWarehouseRepo.save(itemWarehouse);
    }

    public ItemWarehouse updateItem(ItemWarehouse itemWarehouse, Long id) {
        ItemWarehouse newItem = itemWarehouseRepo.findById(id).get();
        Optional<ItemWarehouse> _item = itemWarehouseRepo.findItemWarehouseByNameAndNotId(itemWarehouse.getName(),id);
        if (_item.isPresent())
            throw new NotFoundException("Podana nazwa już istnieje");
        newItem.setName(itemWarehouse.getName());
        return itemWarehouseRepo.save(newItem);
    }

    public void deleteItem(Long id) {
        boolean exist = itemWarehouseRepo.existsById(id);
        if (!exist)
            throw new NotFoundException("W bazie nie ma kategorii o id:" + id);
        itemWarehouseRepo.deleteById(id);
    }
}
