package com.therealcodeshow.cacheApp.repositories;

import com.therealcodeshow.cacheApp.models.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductsEntity, Integer> {
    List<ProductsEntity> findByName(String productName);
}
