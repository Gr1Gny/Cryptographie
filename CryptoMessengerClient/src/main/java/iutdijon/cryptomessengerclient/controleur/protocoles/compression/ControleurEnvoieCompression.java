package iutdijon.cryptomessengerclient.controleur.protocoles.compression;

import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.network.Network;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecrancompression.EcranCompressionEnvoi;

/**
 * Controleur d'envoie pour les algo de compression
 * @author simonetma
 */
public class ControleurEnvoieCompression extends Controleur {

    private EcranCompressionEnvoi ecran;
    private NomProtocole protocole;
    
    /**
     * Constructeur
     * @param ecran écran d'origine
     * @param protocole protocole de compression utilisé
     */
    public ControleurEnvoieCompression(EcranCompressionEnvoi ecran,NomProtocole protocole) {
        this.ecran = ecran;
        this.protocole = protocole;
    }
    
    @Override
    public void avertir() {
        //On envoie le message
        Network.envoyer(ecran.getMessageCompresse());
    }
    
}
