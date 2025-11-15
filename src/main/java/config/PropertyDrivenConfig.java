package config;

import dao.IDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Map;

@Configuration
@PropertySource("classpath:app.properties")
@Profile("prod && dev && file && api")  // Actif seulement quand tous les profils sont actifs
public class PropertyDrivenConfig {

    // Injection de tous les beans IDao indexés par id de bean
    private final Map<String, IDao> candidates;

    public PropertyDrivenConfig(Map<String, IDao> candidates) {
        this.candidates = candidates;
    }

    @Value("${dao.target:dao}")
    private String target;

    @Bean(name = "dao")
    @DependsOn("propertySourcesPlaceholderConfigurer")
    public IDao selectedDao() {
        IDao bean = candidates.get(target);
        if (bean == null) {
            throw new IllegalArgumentException("Implémentation inconnue: " + target + " (dao|dao2|daoFile|daoApi)");
        }
        return bean;
    }

    // Résolution des placeholders @Value sans Spring Boot
    // Configure pour lire aussi les System Properties (priorité sur le fichier)
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        // Les System Properties sont automatiquement lues par Spring Environment
        // et ont priorité sur les fichiers de propriétés
        return configurer;
    }
}

