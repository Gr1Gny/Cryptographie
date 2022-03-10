package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author simonetma
 */
public class ProtocoleRLETestCompression {
    
    public ProtocoleRLETestCompression() {
    }

    /**
     * Test 0 of chiffrer method, of class ProtocoleRLE.
     */
    @Test
    public void testChiffrer0() {
        System.out.println("Test compression : AAAAAAA - 7");
        Protocole protocole = new ProtocoleRLE();
        Message message1 = new Message();
        message1.setCorpsMessage("AAAAAAA");
        protocole.ajouterCle("CLE_COMPRESSION", "7");
        assertEquals("7A",protocole.chiffrer(message1).getCorpsMessage());
    }
    
    /**
     * Test 1 of chiffrer method, of class ProtocoleRLE.
     */
    @Test
    public void testChiffrer1() {
        System.out.println("Test compression : AAAABBBABACCCBBBAAABAAA - 7");
        Protocole protocole = new ProtocoleRLE();
        Message message1 = new Message();
        message1.setCorpsMessage("AAAABBBABACCCBBBAAABAAA");
        protocole.ajouterCle("CLE_COMPRESSION", "7");
        assertEquals("4A3B1A1B1A3C3B3A1B3A",protocole.chiffrer(message1).getCorpsMessage());
    }
    
    /**
     * Test 2 of chiffrer method, of class ProtocoleRLE.
     */
    @Test
    public void testChiffrer2() {
        System.out.println("Test compression : AAAABBBBB - 7");
        Protocole protocole = new ProtocoleRLE();
        Message message1 = new Message();
        message1.setCorpsMessage("AAAABBBBB");
        protocole.ajouterCle("CLE_COMPRESSION", "7");
        assertEquals("4A5B",protocole.chiffrer(message1).getCorpsMessage());
    }
    
    /**
     * Test 3 of chiffrer method, of class ProtocoleRLE.
     */
    @Test
    public void testChiffrer3() {
        System.out.println("Test compression : AAAABBBBB - 3");
        Protocole protocole = new ProtocoleRLE();
        Message message1 = new Message();
        message1.setCorpsMessage("AAAABBBBB");
        protocole.ajouterCle("CLE_COMPRESSION", "3");
        assertEquals("3A1A3B2B",protocole.chiffrer(message1).getCorpsMessage());
    }

    
}
