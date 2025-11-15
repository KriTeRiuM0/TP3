package config;

import dao.IDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Configuration avec @Primary pour définir un choix par défaut
 * Utilisé quand plusieurs beans IDao coexistent sans profils actifs
 */
@Configuration
@Profile("!prod && !dev && !file && !api")  // Actif seulement si aucun profil n'est actif
public class PrimaryConfig {

    /**
     * Bean marqué @Primary - sera injecté par défaut si plusieurs beans IDao sont disponibles
     * Exemple: rendre DaoImpl2 prioritaire
     */
    @Bean
    @Primary
    public IDao primaryDao() {
        return new IDao() {
            @Override
            public double getValue() {
                return 150.0;  // -> 300.0 (même valeur que DaoImpl2)
            }
        };
    }
}

