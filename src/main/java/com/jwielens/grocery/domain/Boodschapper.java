package com.jwielens.grocery.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Boodschapper {
    @Id
    @GeneratedValue
    private Long id;
    private String voornaam;
    private String achternaam;
    private String emailadres;

    public Boodschapper(String voornaam, String achternaam, String emailadres) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.emailadres = emailadres;
    }
}
