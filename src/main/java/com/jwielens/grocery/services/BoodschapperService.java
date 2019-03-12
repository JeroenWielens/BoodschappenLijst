package com.jwielens.grocery.services;

import com.jwielens.grocery.domain.Boodschapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface BoodschapperService {
    ArrayList<Boodschapper> getBoodschappers();

    Boodschapper getBoodschapperById(Long id);

    Boodschapper saveBoodschapper(Boodschapper boodschapper);
}
