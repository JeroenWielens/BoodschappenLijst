package com.jwielens.grocery.bootstrap;

import com.jwielens.grocery.domain.Boodschapper;
import com.jwielens.grocery.domain.Product;
import com.jwielens.grocery.repositories.BoodschapperRepository;
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

//    @Bean
//    CommandLineRunner initDatabase(BoodschapperRepository repository) {
//        return args -> {
//            log.info("preloading " + repository.save(new Boodschapper("Henk", "Hendriks", "henkhendriks@ocs.nl")));
//            log.info("preloading " + repository.save(new Boodschapper("Jan", "Janssen", "janjanssen@ocs.nl")));
//            log.info("preloading " + repository.save(new Boodschapper("Piet", "Pieters", "pietpieters@ocs.nl")));
//        };
//    }
}
