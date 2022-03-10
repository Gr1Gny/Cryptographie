package iutdijon.cryptomessengerclient.controleur.protocoles.compression;

import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.modele.protocoles.FabriqueProtocole;
import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecrancompression.EcranCompressionEnvoi;

/**
 * Controleur de compression
 * @author simonetma
 */
public class ControleurCompression extends Controleur {

    private EcranCompressionEnvoi ecran;
    private NomProtocole protocole;
    
    /**
     * Constructeur
     * @param ecran écran d'origine
     * @param protocole protocole de compression utilisé
     */
    public ControleurCompression(EcranCompressionEnvoi ecran,NomProtocole protocole) {
        this.ecran = ecran;
        this.protocole = protocole;
    }
    
    @Override
    public void avertir() {
        Protocole algoCompression = FabriqueProtocole.create(protocole);
        algoCompression.ajouterCle("CLE_COMPRESSION", ecran.getCle());
        ecran.setMessageCompresse(algoCompression.chiffrer(ecran.getMessageNonCompresse()));
    }
    
}
