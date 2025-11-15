package config;

import dao.DaoApi;
import dao.IDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("api")  // Actif seulement quand le profil "api" est actif (nécessaire pour DaoApi)
public class DaoAliasConfig {
    // Alias "dao" qui renvoie DaoApi comme implémentation
    // Pour changer la cible, modifiez le paramètre (ex: DaoFile target au lieu de DaoApi target)
    // Note: Si la cible a un @Profile, activez le profil correspondant dans Presentation2
    @Bean(name = "dao")
    public IDao daoAlias(DaoApi target) {
        return target; // alias "dao" -> "daoApi"
    }
}

