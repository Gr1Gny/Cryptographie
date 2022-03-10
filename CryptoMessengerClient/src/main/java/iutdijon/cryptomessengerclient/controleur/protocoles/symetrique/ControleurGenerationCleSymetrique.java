package iutdijon.cryptomessengerclient.controleur.protocoles.symetrique;

import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.modele.protocoles.GenerateurCle;
import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecransymetrique.EcranSymetriqueEnvoi;

/**
 * Controleur de génération de clé symétrique
 * @author simonetma
 */
public class ControleurGenerationCleSymetrique extends Controleur {

    
    private EcranSymetriqueEnvoi ecran;
    private NomProtocole protocole;
    
    public ControleurGenerationCleSymetrique(EcranSymetriqueEnvoi ecran,NomProtocole protocole) {
        this.ecran = ecran;
        this.protocole = protocole;
    }
    
    @Override
    public void avertir() {
        switch(protocole) {
            case CESAR:  ecran.setCle(GenerateurCle.genererCleCesar());  break;
            case SUBSTITUTION:  ecran.setCle(GenerateurCle.genererCleSubstitution());  break;
            case TRANSPOSITION:  ecran.setCle(GenerateurCle.genererCleTransposition());  break;
            case VIGENERE:  ecran.setCle(GenerateurCle.genererCleVigenere());  break;
            case FEISTEL:  ecran.setCle(GenerateurCle.genererCleVigenere());  break;
        }
    }
    
}
