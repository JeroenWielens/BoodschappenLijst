package com.jwielens.grocery.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Product {

    public Product(String naam, Integer benodigdeHoeveelheid) {
        this.naam = naam;
        this.benodigdeHoeveelheid = benodigdeHoeveelheid;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String naam;
    private Integer benodigdeHoeveelheid;

    @Builder
    public Product(Long id, String naam, Integer benodigdeHoeveelheid) {
        this.naam = naam;
        this.benodigdeHoeveelheid = benodigdeHoeveelheid;
        this.id = id;
    }

    @Override
    public String toString(){
        return naam + " - benodigde hoeveelheid: " + benodigdeHoeveelheid;
    }


}
