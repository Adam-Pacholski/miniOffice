package eu.adampacholski.miniOffice.item.itemWarehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Item>> get() {
        List<Item> item = itemService.get();
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/byName/{cat}")
    public ResponseEntity<List<Item>> getByName(
            @PathVariable("cat") String cat) {
        List<Item> item = itemService.getByCategory(cat);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<Item> getById(
            @PathVariable("id") Long id
    ){
        Item item = itemService.getById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Item> add(
            @RequestBody Item item) {
        Item newItem = itemService.add(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @PutMapping(path = "/edit/{id}")
    public ResponseEntity<Item> edit(
            @PathVariable("id") Long id,
            @RequestBody Item item
    ) {
        Item editItem = itemService.update(item, id);
        return new ResponseEntity<>(editItem, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Long id
    ) {
        itemService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
