package com.example.config;

import com.example.dao.IDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Classe de configuration pour gérer différentes stratégies d'injection de dépendances
 * Respecte le principe OCP : MetierImpl n'a pas besoin d'être modifié
 */
@Configuration
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class DaoConfiguration {

    /**
     * Stratégie 1 : @Primary - Cette implémentation sera injectée par défaut
     * si aucune autre stratégie n'est spécifiée
     */
    @Bean
    @Primary
    @Profile("!prod && !dev && !file && !api && !property && !alias")  // Actif si aucun autre profil n'est actif
    public IDao daoPrimary() {
        System.out.println("Configuration : Création du bean Primary (valeur par défaut)");
        return new IDao() {
            @Override
            public double getValue() {
                System.out.println("Version Primary - Implémentation par défaut");
                return 50.0;
            }
        };
    }

    /**
     * Stratégie 2 : Alias via @Bean - Crée un bean avec un alias spécifique
     * Peut être référencé via @Qualifier("daoAlias") si nécessaire
     */
    @Bean("daoAlias")
    @Profile("alias")
    public IDao daoAlias() {
        System.out.println("Configuration : Création du bean Alias");
        return new IDao() {
            @Override
            public double getValue() {
                System.out.println("Version Alias - Implémentation via alias de configuration");
                return 75.0;
            }
        };
    }

    /**
     * Stratégie 3 : Sélection par propriété externe (@PropertySource + @Value)
     * Cette méthode crée un bean conditionnel basé sur la propriété dao.implementation
     * Utilise @Primary pour être sélectionné automatiquement quand le profil "property" est actif
     */
    @Bean
    @Primary
    @Profile("property")
    public IDao daoPropertyBased(
            @Value("${dao.implementation}") String implementationType) {
        System.out.println("Configuration : Bean Primary basé sur propriété - " + implementationType);
        
        // Retourne une implémentation basée sur la propriété
        return new IDao() {
            @Override
            public double getValue() {
                System.out.println("Version Property-Based - " + implementationType);
                switch (implementationType) {
                    case "dao":
                        return 100.0;  // → calcul() = 200.0
                    case "dao2":
                        return 150.0;  // → calcul() = 300.0
                    case "daoFile":
                        return 180.0;  // → calcul() = 360.0
                    case "daoApi":
                        return 220.0;  // → calcul() = 440.0
                    case "daoAlias":
                        return 75.0;
                    default:
                        return 50.0;
                }
            }
        };
    }
}

