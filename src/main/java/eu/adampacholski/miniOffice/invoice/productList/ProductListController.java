package eu.adampacholski.miniOffice.invoice.productList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/product-list")
public class ProductListController {
    private final ProductListService productListService;

    public ProductListController(ProductListService productListService) {
        this.productListService = productListService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductList>> get() {
        List<ProductList> item = productListService.get();
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
