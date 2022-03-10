package iutdijon.cryptomessengerclient.controleur.protocoles.compression;

import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.modele.protocoles.GenerateurCle;
import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecrancompression.EcranCompressionEnvoi;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecransymetrique.EcranSymetriqueEnvoi;

/**
 * Controleur de génération de clé pour les algo de compression
 * @author simonetma
 */
public class ControleurGenerationCleCompression extends Controleur {

    
    private EcranCompressionEnvoi ecran;
    private NomProtocole protocole;
    
    public ControleurGenerationCleCompression(EcranCompressionEnvoi ecran,NomProtocole protocole) {
        this.ecran = ecran;
        this.protocole = protocole;
    }
    
    @Override
    public void avertir() {
        switch(protocole) {
            case RLE:  ecran.setCle(GenerateurCle.genererCleRLE());  break;
        }
    }
    
}
