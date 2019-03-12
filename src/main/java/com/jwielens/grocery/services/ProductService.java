package com.jwielens.grocery.services;

import com.jwielens.grocery.domain.Product;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ProductService {

    Product findById(Long id);

    Product saveOrUpdate(Product product);

    void delete(Long id);

    Set<Product> getProducten();

    Boolean checkDuplicates(Product product);

}
