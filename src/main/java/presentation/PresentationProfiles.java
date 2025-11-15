package presentation;

import com.example.metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Démonstration de l'injection par profils Spring
 * Changez le profil actif pour tester différentes implémentations
 */
@Configuration
@ComponentScan(basePackages = {"com.example.dao", "com.example.metier"})
public class PresentationProfiles {
    public static void main(String[] args) {
        // Choisissez le profil à activer : "dev", "prod", "file", ou "api"
        String activeProfile = args.length > 0 ? args[0] : "dev";
        
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.getEnvironment().setActiveProfiles(activeProfile);
            context.register(PresentationProfiles.class);
            context.refresh();
            
            IMetier metier = context.getBean(IMetier.class);
            System.out.println("=== Profil actif : " + activeProfile + " ===");
            System.out.println("Résultat = " + metier.calcul());
        }
    }
}

