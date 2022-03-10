package iutdijon.cryptomessengerclient.controleur.protocoles.symetrique;

import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.modele.protocoles.FabriqueProtocole;
import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecransymetrique.EcranSymetriqueReception;

/**
 * Controleur appelé lors du déchiffrement d'un message par un algo symétrique
 * @author simonetma
 */
public class ControleurDechiffrementSymetrique extends Controleur {

    private EcranSymetriqueReception ecran;
    private NomProtocole protocole;
    
    public ControleurDechiffrementSymetrique(EcranSymetriqueReception ecran,NomProtocole protocole) {
        this.ecran = ecran;
        this.protocole = protocole;
    }
    
    @Override
    public void avertir() {
        Protocole algoSymetrique = FabriqueProtocole.create(protocole);
        algoSymetrique.ajouterCle("CLE_SYMETRIQUE", ecran.getCle());
        ecran.setMessageDechiffre(algoSymetrique.dechiffrer(ecran.getMessageChiffre()));
    }
    
}
