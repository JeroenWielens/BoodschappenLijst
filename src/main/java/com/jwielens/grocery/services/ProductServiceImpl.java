package com.jwielens.grocery.services;

import com.jwielens.grocery.domain.Product;
import com.jwielens.grocery.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Set<Product> getProducten() {
        Set<Product> products = new HashSet<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Boolean checkDuplicates(Product product) {
        Boolean isDup = false;
        Set<Product> products = new HashSet<>();
        productRepository.findAll().forEach(products::add);

        for (Product product1 : products) {
            if (product1.getNaam().equalsIgnoreCase(product.getNaam())){
                isDup = true;
            }
        }
        return isDup;
    }

}
