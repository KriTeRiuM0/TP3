package presentation;

import com.example.metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Démonstration des différentes stratégies d'injection
 * Affiche les valeurs attendues : 200.0, 300.0, 360.0, 440.0
 */
@Configuration
@ComponentScan(basePackages = {"com.example.dao", "com.example.metier"})
public class PresentationDemo {
    public static void main(String[] args) {
        System.out.println("=== Démonstration des stratégies d'injection ===\n");
        
        // Test avec profil "prod" → 200.0
        testProfile("prod", "Production");
        
        // Test avec profil "dev" → 300.0
        testProfile("dev", "Développement");
        
        // Test avec profil "file" → 360.0
        testProfile("file", "Fichier");
        
        // Test avec profil "api" → 440.0
        testProfile("api", "API");
    }
    
    private static void testProfile(String profile, String description) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.getEnvironment().setActiveProfiles(profile);
            context.register(PresentationDemo.class);
            context.refresh();
            
            IMetier metier = context.getBean(IMetier.class);
            double result = metier.calcul();
            System.out.println(description + " (" + profile + ") : Résultat = " + result);
            System.out.println("---\n");
        }
    }
}

