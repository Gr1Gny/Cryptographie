package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author simonetma
 */
public class TranspositionRemplissageTableauTest {
    
    public TranspositionRemplissageTableauTest() {
    }

    /**
     * Test of remplirTableauChiffrement method, of class ProtocoleTransposition.
     * @throws java.lang.Exception
     */
    @Test
    public void testRemplirTableauChiffrement() throws Exception {
        System.out.println("Test du remplissage du tableau sans bourrage");
        String message = "Bonjour comment ca va ?";
        String cle = "Chat";
        
        ProtocoleTransposition instance = new ProtocoleTransposition();
        Method methode = instance.getClass().getDeclaredMethod("remplirTableauChiffrement", String.class,String.class);
        methode.setAccessible(true);
        char[][] result = (char[][]) methode.invoke(instance, message,cle);
        
        char[][] expResult = {
            {'B','o','n','j'},
            {'o','u','r',' '},
            {'c','o','m','m'},
            {'e','n','t',' '},
            {'c','a',' ','v'},
            {'a',' ','?','x'},
        };
        
        assertArrayEquals(expResult,result);
    }

    
}
