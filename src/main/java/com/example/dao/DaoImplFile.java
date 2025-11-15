package com.example.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("daoFile")
@Profile("file")  // Ce bean sera actif uniquement avec le profil "file"
public class DaoImplFile implements IDao {
    @Override
    public double getValue() {
        System.out.println("Version File - Lecture depuis un fichier");
        return 180.0;  // Retourne 180.0 → calcul() = 360.0
    }
}

