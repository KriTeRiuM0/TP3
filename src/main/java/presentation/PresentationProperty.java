package presentation;

import com.example.config.DaoConfiguration;
import com.example.metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Démonstration de l'injection via propriété externe (@PropertySource + @Value)
 * L'implémentation est sélectionnée selon la valeur dans application.properties
 */
@Configuration
@ComponentScan(basePackages = {"com.example.dao", "com.example.metier"})
@Import(DaoConfiguration.class)
public class PresentationProperty {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.getEnvironment().setActiveProfiles("property");
            context.register(PresentationProperty.class);
            context.refresh();
            
            IMetier metier = context.getBean(IMetier.class);
            System.out.println("=== Stratégie : Propriété externe (@PropertySource) ===");
            System.out.println("Résultat = " + metier.calcul());
            System.out.println("Modifiez dao.implementation dans application.properties pour changer l'implémentation");
        }
    }
}

