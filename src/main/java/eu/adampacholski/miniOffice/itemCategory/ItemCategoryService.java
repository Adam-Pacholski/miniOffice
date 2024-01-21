package eu.adampacholski.miniOffice.itemCategory;

import eu.adampacholski.miniOffice.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCategoryService {

    private final ItemCategoryRepo itemCategoryRepo;

    @Autowired
    public ItemCategoryService(ItemCategoryRepo itemCategoryRepo) {
        this.itemCategoryRepo = itemCategoryRepo;
    }

    public List<ItemCategory> getItemsCategory() {
        return itemCategoryRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public ItemCategory addItemCategory(ItemCategory itemCategory) {
        Optional<ItemCategory> _item = itemCategoryRepo.findItemCategoryByName(itemCategory.getName());
        if (_item.isPresent())
            throw new NotFoundException("Podana nazwa już istnieje");
        return itemCategoryRepo.save(itemCategory);
    }

    public ItemCategory updateItemCategory(ItemCategory itemCategory, Long id) {
        ItemCategory newItemCategory = itemCategoryRepo.findById(id).get();
        Optional<ItemCategory> _item = itemCategoryRepo.findItemCategoryByName(itemCategory.getName());
        if (_item.isPresent())
            throw new NotFoundException("Podana nazwa już istnieje");
        newItemCategory.setName(itemCategory.getName());
        return itemCategoryRepo.save(newItemCategory);
    }

    public void deleteItemCategory(Long id) {
        boolean exist = itemCategoryRepo.existsById(id);
        if (!exist)
            throw new NotFoundException("W bazie nie ma kategorii o id:" + id);
        itemCategoryRepo.deleteById(id);
    }
}
