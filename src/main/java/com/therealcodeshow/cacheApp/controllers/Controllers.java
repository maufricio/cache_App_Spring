package com.therealcodeshow.cacheApp.controllers;

import com.therealcodeshow.cacheApp.models.ProductsEntity;
import com.therealcodeshow.cacheApp.repositories.ProductRepository;
import com.therealcodeshow.cacheApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/products", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class Controllers {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    /*@GetMapping("/getAll")
    public ResponseEntity<List<ProductsEntity>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.productService.getAllProducts());
    }*/

    @GetMapping("/getAll")
    public List<ProductsEntity> getAllProducts() {

        return this.productService.getAllProducts();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ProductsEntity> getById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.productService.getProductById(Integer.parseInt(id)));
    }

    @GetMapping("/getById")
    public ResponseEntity<ProductsEntity> getByIdParam(@RequestParam("id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.productService.getProductById(Integer.parseInt(id)));
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody ProductsEntity product) {
        productService.saveProduct(product);
        return "Product added successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        this.productService.deleteProductById(Integer.parseInt(id));
        return "Product deleted successfully";
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") String id, @RequestBody ProductsEntity product) {
        ProductsEntity productUpdated = this.productService.updateProductById(Integer.parseInt(id), product);
        if (productUpdated != null) {
            return "Product updated successfully";
        } else {
            return "Product not found";
        }
    }
}
