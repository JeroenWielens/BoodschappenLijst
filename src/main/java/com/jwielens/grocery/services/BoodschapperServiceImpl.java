package com.jwielens.grocery.services;

import com.jwielens.grocery.domain.Boodschapper;
import com.jwielens.grocery.repositories.BoodschapperRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoodschapperServiceImpl implements BoodschapperService {
    BoodschapperRepository boodschapperRepository;

    public BoodschapperServiceImpl(BoodschapperRepository repository) {
        this.boodschapperRepository = repository;
    }

    @Override
    public ArrayList<Boodschapper> getBoodschappers() {
        ArrayList<Boodschapper> boodschappers = new ArrayList<>();
        boodschapperRepository.findAll().forEach(boodschappers::add);
        return boodschappers;
    }

    @Override
    public Boodschapper getBoodschapperById(Long id) {
        return boodschapperRepository.findById(id).orElse(null);
    }

    @Override
    public Boodschapper saveBoodschapper(Boodschapper boodschapper) {
        boodschapperRepository.save(boodschapper);
        return boodschapper;
    }

    @Override
    public void delete(Long id) {
        boodschapperRepository.deleteById(id);
    }
}
