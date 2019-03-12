package com.jwielens.grocery.repositories;

import com.jwielens.grocery.domain.Boodschapper;
import org.springframework.data.repository.CrudRepository;

public interface BoodschapperRepository extends CrudRepository<Boodschapper, Long> {
}
