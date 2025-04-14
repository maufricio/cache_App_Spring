package com.therealcodeshow.cacheApp.service;

import com.therealcodeshow.cacheApp.models.ProductsEntity;
import com.therealcodeshow.cacheApp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // A function to update whenever we save a new record to DB & update the cache
    @CacheEvict(value = "allProducts", allEntries = true)
    public ProductsEntity saveProduct(ProductsEntity product) {
        return productRepository.save(product);
    }

    // A function to get all products from DB and store it in the cache
    @Cacheable(value = "allProducts")
    public List<ProductsEntity> getAllProducts () {
        return productRepository.findAll();
    }

    // A function to delete a product from DB and update the cache: both "product" and "allProducts" places
    @Caching(
            evict = {
                    @CacheEvict(value = "products", key = "#id"), // Evict the cache for the specific product
                    @CacheEvict(value = "allProducts", allEntries = true) // Evict all products cache
            }
    )
    public void deleteProductById(int id) {
        this.productRepository.deleteById(id);
    }

    // A function to get a product by id from DB and store it in the cache in the place "products"
    @Cacheable(value = "products", key = "#id") // Users is the name of the cache
    public ProductsEntity getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    // A function to update a product by id in DB and update the cache in the place "products"
    @CachePut(value = "products", key = "#id") // Update the cache with the new product
            public ProductsEntity updateProductById(int id, ProductsEntity product) {
                ProductsEntity existingProduct = this.productRepository.findById(id).orElse(null);
                if (existingProduct != null) {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
            return productRepository.save(existingProduct);
        }
        return null;
    }

}
