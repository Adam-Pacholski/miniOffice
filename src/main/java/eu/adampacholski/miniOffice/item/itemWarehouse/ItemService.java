package eu.adampacholski.miniOffice.item.itemWarehouse;

import eu.adampacholski.miniOffice.Exception.NotFoundException;
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

    public List<Item> get() {
        return itemRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
    public List<Item> getByCategory(String category) {
        return itemRepo.findItemsByCategory(category, Sort.by(Sort.Direction.ASC, "name"));
    }

    public Item getById(Long id) {
        return itemRepo.findById(id).get();
    }

    public Item add(Item item) {
        Optional<Item> _item = itemRepo.findByPartNumber(item.getPartNumber());
        if (_item.isPresent())
            throw new NotFoundException("Podana część już istnieje");
        return itemRepo.save(item);
    }

    public Item update(Item item, Long id) {
        Item newItem = itemRepo.findById(id).get();
        Optional<Item> _item = itemRepo.findItemByPartNumberAndOntId(item.getPartNumber(), id);
        if (_item.isPresent())
            throw new NotFoundException("Podany przedmiot już istnieje");

        newItem.setPartNumber(item.getPartNumber());
        newItem.setName(item.getName());
        newItem.setDescription(item.getDescription());
        newItem.setPrice(item.getPrice());
        newItem.setAmount(item.getAmount());
        newItem.setItemCategory(item.getItemCategory());
        newItem.setItemType(item.getItemType());
        newItem.setItemUnit(item.getItemUnit());

        return itemRepo.save(newItem);
    }

    public void delete(Long id) {
        boolean exist = itemRepo.existsById(id);
        if (!exist)
            throw new NotFoundException("W bazie nie ma kategorii o id:" + id);
        itemRepo.deleteById(id);
    }
}
