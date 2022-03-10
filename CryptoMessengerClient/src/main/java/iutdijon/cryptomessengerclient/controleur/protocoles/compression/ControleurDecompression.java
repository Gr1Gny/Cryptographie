package iutdijon.cryptomessengerclient.controleur.protocoles.compression;

import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.modele.protocoles.FabriqueProtocole;
import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecrancompression.EcranCompressionReception;

/**
 * Controleur de décompression
 * @author simonetma
 */
public class ControleurDecompression extends Controleur {
    
    private EcranCompressionReception ecran;
    private NomProtocole protocole;
    
    /**
     * Constructeur
     * @param ecran écran d'origine
     * @param protocole protocole de compression utilisé
     */
    public ControleurDecompression(EcranCompressionReception ecran,NomProtocole protocole) {
        this.ecran = ecran;
        this.protocole = protocole;
    }
    
    @Override
    public void avertir() {
        Protocole algoDecompression = FabriqueProtocole.create(protocole);
        algoDecompression.ajouterCle("CLE_COMPRESSION", ecran.getCle());
        ecran.setMessageDecompresse(algoDecompression.dechiffrer(ecran.getMessageCompresse()));
    }
    
}
