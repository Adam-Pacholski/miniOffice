package eu.adampacholski.miniOffice.item.itemWarehouse.itemUnit;

import eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse.ItemWarehouse;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse.ItemWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/item-unit")
public class ItemUnitController {
    private final ItemUnitService itemUnitService;
@Autowired
    public ItemUnitController(ItemUnitService itemUnitService) {
        this.itemUnitService = itemUnitService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItemUnit>> getItem() {
        List<ItemUnit> item = itemUnitService.getItems();
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<ItemUnit> getItemById(
            @PathVariable("id") Long id
    ){
        ItemUnit item = itemUnitService.getItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ItemUnit> addCustomer(
            @RequestBody ItemUnit item) {
        ItemUnit newItem = itemUnitService.addItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @PutMapping(path = "/edit/{id}")
    public ResponseEntity<ItemUnit> editCountry(
            @PathVariable("id") Long id,
            @RequestBody ItemUnit item
    ) {
        ItemUnit editItem = itemUnitService.updateItem(item, id);
        return new ResponseEntity<>(editItem, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteCountry(
            @PathVariable("id") Long id
    ) {
        itemUnitService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
