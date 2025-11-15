package presentation;

import com.example.config.DaoConfiguration;
import com.example.metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Démonstration de l'injection via alias (@Bean avec nom spécifique)
 * Le bean "daoAlias" sera injecté grâce au profil "alias"
 */
@Configuration
@ComponentScan(basePackages = {"com.example.dao", "com.example.metier"})
@Import(DaoConfiguration.class)
public class PresentationAlias {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.getEnvironment().setActiveProfiles("alias");
            context.register(PresentationAlias.class);
            context.refresh();
            
            IMetier metier = context.getBean(IMetier.class);
            System.out.println("=== Stratégie : Alias (@Bean) ===");
            System.out.println("Résultat = " + metier.calcul());
        }
    }
}

