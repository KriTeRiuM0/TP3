package com.example.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("daoApi")
@Profile("api")  // Ce bean sera actif uniquement avec le profil "api"
public class DaoImplApi implements IDao {
    @Override
    public double getValue() {
        System.out.println("Version API - Appel à une API externe");
        return 220.0;  // Retourne 220.0 → calcul() = 440.0
    }
}

