package iutdijon.cryptomessengerclient.modele.protocoles;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author simonetma
 */
public class GenerateurCleTest {
    
    /**
     * Test of gerererCleTransposition method, of class GenerateurCle.
     */
    @org.junit.jupiter.api.Test
    public void testGerererCleTransposition() {
        System.out.println("Test de la génération de clé de transposition");
        for(int i = 0; i<100;i++) {
            String result = GenerateurCle.genererCleTransposition();
            assertTrue(result.length()>=5,"Clé générée de taille trop petite : "+result);
            assertTrue(result.length()<=20,"Clé générée de taille trop grande : "+result);
            assertTrue(result.matches("[a-zA-Z]+"),"Clé générée contenant des caractères non valides :"+result);
        }
    }
    
}
