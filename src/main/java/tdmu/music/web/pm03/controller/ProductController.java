package tdmu.music.web.pm03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import tdmu.music.web.pm03.dto.ProductDto;
import tdmu.music.web.pm03.model.Product;
import tdmu.music.web.pm03.service.CategoryService;
import tdmu.music.web.pm03.service.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;


    @GetMapping("/admin/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String getAddProduct(Model model) {
        model.addAttribute("productDTO", new ProductDto());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String postAddProduct(@ModelAttribute("ProductDTO") ProductDto productDto,
                                 @RequestParam("productImage") MultipartFile file,
                                 @RequestParam("priceFile") MultipartFile priceFile,
                                 @RequestParam("price") String price,
                                 @RequestParam("imgName") String imgName) throws IOException {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setCategory(categoryService.getCategoryById(productDto.getCategoryId()).get());
//        product.setPrice(productDto.getPrice());
        product.setWeight(productDto.getWeight());
        product.setDescription(productDto.getDescription());
        String imageUUID;
        String priceUUID;
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());

        } else {
            imageUUID = imgName;

        }
        if (!priceFile.isEmpty()) {
            priceUUID = priceFile.getOriginalFilename();
            Path filePriceAndPath = Paths.get(uploadDir, priceUUID);
            Files.write(filePriceAndPath, priceFile.getBytes());
        } else {
            priceUUID = price;
        }

        product.setImageName(imageUUID);
        product.setPrice(priceUUID);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deteleProducts(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id).get();
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setWeight(product.getWeight());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(productDto.getDescription());
        product.setImageName(product.getImageName());

        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productDTO", productDto);
        return "productsAdd";
    }

    @GetMapping("/admin/product")
    public String search(ModelMap model, @RequestParam(name = "name",
            required = false) String name) {
        List<Product> list = null;
        if (StringUtils.hasText(name)) {
            list = productService.searchProductByName(name);
        } else {
            list = productService.getAllProducts();
        }
        model.addAttribute("products", list);
        return "products";
    }

//    @GetMapping("/admin/product")
//    public String search1(ModelMap model, @RequestParam(name = "name",
//            // phan biet trang hien tai
//            required = false) String name, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
//        int currentPage = page.orElse(1); // kich thuoc cua trang
//        int pageSize = size.orElse(3);
//        // tao ra doi tuong// sap xep theo nhung tieu chi nao
//        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
//        Page<Product> resultPage = null;
//        if (StringUtils.hasText(name)) // kiem tra gia tri cua name truyen ve co noi dung hay khong
//        {
//            resultPage = productService.findByNameContaining(name, pageable);
//            model.addAttribute("name", name);
//        } else {
//            resultPage = productService.findAll(pageable);
//        }
//        int totalPages = resultPage.getTotalPages();
//        if (totalPages > 0) {
//            int start = Math.max(1, currentPage - 2);
//            int end = Math.min(totalPages + 2, totalPages);
//
//
//            if (totalPages > 5) {
//                if (end == totalPages) start = end - 5;
//                else if (start == 1) end = start + 5;
//            }
//            List<Integer> pageNumbers = IntStream.range(start, end).boxed().collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//        model.addAttribute("productPage", resultPage);
//        return "products";
//
//
//    }
}
