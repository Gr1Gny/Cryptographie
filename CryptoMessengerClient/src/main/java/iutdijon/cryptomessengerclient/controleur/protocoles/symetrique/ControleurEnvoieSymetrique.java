package iutdijon.cryptomessengerclient.controleur.protocoles.symetrique;

import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.network.Network;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecransymetrique.EcranSymetriqueEnvoi;

/**
 * Controleur appelé lors de l'envoie d'un message par l'écran du protocole Texte Brut
 * @author simonetma
 */
public class ControleurEnvoieSymetrique extends Controleur {

    private EcranSymetriqueEnvoi ecran;
    private NomProtocole protocole;
    
    public ControleurEnvoieSymetrique(EcranSymetriqueEnvoi ecran,NomProtocole protocole) {
        this.ecran = ecran;
        this.protocole = protocole;
    }
    
    @Override
    public void avertir() {
        //On envoie le message
        Network.envoyer(ecran.getMessageChiffre());
    }
    
}
