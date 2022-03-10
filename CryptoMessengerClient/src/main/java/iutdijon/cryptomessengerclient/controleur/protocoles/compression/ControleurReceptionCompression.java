package iutdijon.cryptomessengerclient.controleur.protocoles.compression;

import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.network.Network;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecrancompression.EcranCompressionReception;

/**
 * Controleur de réception d'un algo de décompression
 * @author simonetma
 */
public class ControleurReceptionCompression extends Controleur {
    
    private EcranCompressionReception ecran;
    private NomProtocole protocole;
    
    /**
     * Constructeur
     * @param ecran écran d'origine
     * @param protocole protocole de compression utilisé
     */
    public ControleurReceptionCompression(EcranCompressionReception ecran,NomProtocole protocole) {
        this.ecran = ecran;
        this.protocole = protocole;
    }
    
    @Override
    public void avertir() {
        Network.recevoir(ecran.getMessageCompresse());
    }
    
}
