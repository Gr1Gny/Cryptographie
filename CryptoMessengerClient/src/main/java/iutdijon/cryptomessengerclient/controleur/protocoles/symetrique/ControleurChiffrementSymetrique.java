package iutdijon.cryptomessengerclient.controleur.protocoles.symetrique;

import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.modele.protocoles.FabriqueProtocole;
import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecransymetrique.EcranSymetriqueEnvoi;

/**
 * Controleur appelé lors du chiffrement d'un message par un algo symétrique
 * @author simonetma
 */
public class ControleurChiffrementSymetrique extends Controleur {

    private EcranSymetriqueEnvoi ecran;
    private NomProtocole protocole;
    
    public ControleurChiffrementSymetrique(EcranSymetriqueEnvoi ecran,NomProtocole protocole) {
        this.ecran = ecran;
        this.protocole = protocole;
    }
    
    @Override
    public void avertir() {
        Protocole algoSymetrique = FabriqueProtocole.create(protocole);
        algoSymetrique.ajouterCle("CLE_SYMETRIQUE", ecran.getCle());
        ecran.setMessageChiffre(algoSymetrique.chiffrer(ecran.getMessageClair()));
    }
    
}
