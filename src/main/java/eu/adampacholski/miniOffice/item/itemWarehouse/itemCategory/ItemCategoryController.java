package eu.adampacholski.miniOffice.item.itemWarehouse.itemCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/item-category")
public class ItemCategoryController {

    private final ItemCategoryService itemCategoryService;

    @Autowired
    public ItemCategoryController(ItemCategoryService itemCategoryService) {
        this.itemCategoryService = itemCategoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItemCategory>> get() {
        List<ItemCategory> itemCategories = itemCategoryService.getItemsCategory();
        return new ResponseEntity<>(itemCategories, HttpStatus.OK);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<ItemCategory> getById(
            @PathVariable("id") Long id
    ){
        ItemCategory itemCategory = itemCategoryService.getItemCategoryById(id);
        return new ResponseEntity<>(itemCategory, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ItemCategory> add(
            @RequestBody ItemCategory itemCategory) {
        ItemCategory newItemCategory = itemCategoryService.addItemCategory(itemCategory);
        return new ResponseEntity<>(newItemCategory, HttpStatus.CREATED);
    }

    @PutMapping(path = "/edit/{id}")
    public ResponseEntity<ItemCategory> edit(
            @PathVariable("id") Long id,
            @RequestBody ItemCategory itemCategory
    ) {
        ItemCategory editItemCategory = itemCategoryService.updateItemCategory(itemCategory, id);
        return new ResponseEntity<>(editItemCategory, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Long id
    ) {
        itemCategoryService.deleteItemCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
