package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author simonetma
 */
public class ProtocoleRLETestDecompression {
    
    public ProtocoleRLETestDecompression() {
    }


    /**
     * Test 0 of dechiffrer method, of class ProtocoleRLE.
     */
    @Test
    public void testDeChiffrer0() {
        System.out.println("Test décompression : AAAAAAA - 7");
        Protocole protocole = new ProtocoleRLE();
        Message message1 = new Message();
        message1.setCorpsMessage("7A");
        protocole.ajouterCle("CLE_COMPRESSION", "7");
        assertEquals("AAAAAAA",protocole.dechiffrer(message1).getCorpsMessage());
    }
    
    /**
     * Test 1 of dechiffrer method, of class ProtocoleRLE.
     */
    @Test
    public void testDeChiffrer1() {
        System.out.println("Test décompression : AAAABBBABACCCBBBAAABAAA - 7");
        Protocole protocole = new ProtocoleRLE();
        Message message1 = new Message();
        message1.setCorpsMessage("4A3B1A1B1A3C3B3A1B3A");
        protocole.ajouterCle("CLE_COMPRESSION", "7");
        assertEquals("AAAABBBABACCCBBBAAABAAA",protocole.dechiffrer(message1).getCorpsMessage());
    }
    
    /**
     * Test 2 of dechiffrer method, of class ProtocoleRLE.
     */
    @Test
    public void testDeChiffrer2() {
        System.out.println("Test décompression : AAAABBBBB - 7");
        Protocole protocole = new ProtocoleRLE();
        Message message1 = new Message();
        message1.setCorpsMessage("4A5B");
        protocole.ajouterCle("CLE_COMPRESSION", "7");
        assertEquals("AAAABBBBB",protocole.dechiffrer(message1).getCorpsMessage());
    }
    
    /**
     * Test 3 of dechiffrer method, of class ProtocoleRLE.
     */
    @Test
    public void testDeChiffrer3() {
        System.out.println("Test compression : AAAABBBBB - 3");
        Protocole protocole = new ProtocoleRLE();
        Message message1 = new Message();
        message1.setCorpsMessage("3A1A3B2B");
        protocole.ajouterCle("CLE_COMPRESSION", "3");
        assertEquals("AAAABBBBB",protocole.dechiffrer(message1).getCorpsMessage());
    }
    
}
