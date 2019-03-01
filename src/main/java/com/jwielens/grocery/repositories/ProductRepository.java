package com.jwielens.grocery.repositories;

import com.jwielens.grocery.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
