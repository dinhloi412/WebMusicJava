package tdmu.music.web.pm03.apicontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdmu.music.web.pm03.model.Product;
import tdmu.music.web.pm03.service.CategoryService;
import tdmu.music.web.pm03.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductAPIController {
    @Autowired
    ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<List<Product>> listAllProducts(){
        List<Product> productList=productService.getAllProducts();
        if(productList.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }
}
