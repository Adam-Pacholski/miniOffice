package eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/item-warehaus")
public class ItemWarenhouseController {
    private final ItemWarehouseService itemWarehouseService;
    @Autowired
    public ItemWarenhouseController(ItemWarehouseService itemWarehouseService) {
        this.itemWarehouseService = itemWarehouseService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<ItemWarehouse>> get() {
        List<ItemWarehouse> itemWarehouses = itemWarehouseService.getItems();
        return new ResponseEntity<>(itemWarehouses, HttpStatus.OK);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<ItemWarehouse> getById(
            @PathVariable("id") Long id
    ){
        ItemWarehouse itemWarehouse = itemWarehouseService.getItemById(id);
        return new ResponseEntity<>(itemWarehouse, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ItemWarehouse> add(
            @RequestBody ItemWarehouse itemWarehouse) {
        ItemWarehouse newItem = itemWarehouseService.addItem(itemWarehouse);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @PutMapping(path = "/edit/{id}")
    public ResponseEntity<ItemWarehouse> edit(
            @PathVariable("id") Long id,
            @RequestBody ItemWarehouse itemWarehouse
    ) {
        ItemWarehouse editItem = itemWarehouseService.updateItem(itemWarehouse, id);
        return new ResponseEntity<>(editItem, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Long id
    ) {
        itemWarehouseService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
