package presentation;

import metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.*;

/**
 * Démonstration de la variante A - @Primary
 * Utilise @Primary pour définir un choix par défaut quand plusieurs beans IDao coexistent
 */
@Configuration
@ComponentScan(basePackages = {"dao","metier","config"})
public class PresentationPrimary {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        // Aucun profil actif - @Primary sera utilisé
        // PrimaryConfig créera un bean @Primary qui sera injecté automatiquement

        ctx.register(PresentationPrimary.class);
        ctx.refresh();

        IMetier metier = ctx.getBean(IMetier.class);
        System.out.println("=== Variante A : @Primary ===");
        System.out.println("Résultat = " + metier.calcul());
        System.out.println("Le bean @Primary a été injecté automatiquement");
        ctx.close();
    }
}
