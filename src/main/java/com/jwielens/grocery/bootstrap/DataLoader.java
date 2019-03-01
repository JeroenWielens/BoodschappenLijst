package com.jwielens.grocery.bootstrap;

import com.jwielens.grocery.domain.Product;
import com.jwielens.grocery.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class DataLoader {
//    @Bean
//    CommandLineRunner initDatabase(ProductRepository repository) {
//        return args -> {
//            log.info("Preloading " + repository.save(new Product("brood", 0)));
//            log.info("Preloading " + repository.save(new Product("kaas", 0)));
//            log.info("Preloading " + repository.save(new Product("vlees", 0)));
//        };
//    }
}
