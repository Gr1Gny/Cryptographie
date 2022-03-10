package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author simonetma
 */
public class TranspositionBourrageTest {
    
    public TranspositionBourrageTest() {
    }

    /**
     * Test of remplirTableauChiffrement method, of class ProtocoleTransposition.
     * @throws java.lang.Exception
     */
    @Test
    public void testBourrage() throws Exception {
        System.out.println("Test du bourrage aléatoire seedé");
        String message = "Bonjour comment ca va ?";
        String cle = "Chat";
        
        ProtocoleTransposition instance = new ProtocoleTransposition();
        Method methode = instance.getClass().getDeclaredMethod("remplirTableauChiffrement", String.class,String.class);
        methode.setAccessible(true);
        char[][] result1 = (char[][]) methode.invoke(instance, message,cle);
        char[][] result2 = (char[][]) methode.invoke(instance, message,cle);
        char[][] result3 = (char[][]) methode.invoke(instance, message,cle);
        char[][] result4 = (char[][]) methode.invoke(instance, message,cle);
        
        //Test de seedage
        assertArrayEquals(result1,result2);
        assertArrayEquals(result1,result3);
        assertArrayEquals(result1,result4);
    }

    
}
