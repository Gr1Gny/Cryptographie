package iutdijon.cryptomessengerclient.modele.protocoles;

import iutdijon.cryptomessengerclient.modele.protocoles.realisations.ProtocoleBrut;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.ProtocoleCesar;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.ProtocoleFeistel;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.ProtocoleHuffman;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.ProtocoleRLE;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.ProtocoleSubstitution;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.ProtocoleTransposition;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.ProtocoleVigenere;


/**
 * Fabrique des protocoles
 * @author simonetma
 */
public class FabriqueProtocole {
    
    /**
     * Fabrique un protocole
     * @param nom nom du protocole
     * @return le protocole
     */
    public static Protocole create(NomProtocole nom) {
        Protocole protocole = null;
        switch(nom) {
            case BRUT: protocole = new ProtocoleBrut();break;
            case CESAR: protocole = new ProtocoleCesar();break;
            case SUBSTITUTION: protocole = new ProtocoleSubstitution();break;
            case TRANSPOSITION: protocole = new ProtocoleTransposition();break;
            case VIGENERE: protocole = new ProtocoleVigenere();break;
            case FEISTEL: protocole = new ProtocoleFeistel();break;
            case RLE: protocole = new ProtocoleRLE();break;
            case HUFFMAN: protocole = new ProtocoleHuffman();break;
        }
        return protocole;
    }
    
}
