package eu.adampacholski.miniOffice.invoice.productList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductListService {
    private final ProductListRepo productListRepo;

    @Autowired
    public ProductListService(ProductListRepo productListRepo) {
        this.productListRepo = productListRepo;
    }

    public List<ProductList> get() {
        return productListRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}
