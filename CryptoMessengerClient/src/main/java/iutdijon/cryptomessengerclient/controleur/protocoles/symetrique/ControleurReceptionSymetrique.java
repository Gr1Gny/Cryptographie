package iutdijon.cryptomessengerclient.controleur.protocoles.symetrique;

import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.network.Network;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecransymetrique.EcranSymetriqueReception;

/**
 * Controleur appelé lors de la réception d'un message
 * @author simonetma
 */
public class ControleurReceptionSymetrique extends Controleur {

    private EcranSymetriqueReception ecran;
    
    public ControleurReceptionSymetrique(EcranSymetriqueReception ecran) {
        this.ecran = ecran;
    }
    
    
    @Override
    public void avertir() {
        Network.recevoir(ecran.getMessageChiffre());
    }
    
}
