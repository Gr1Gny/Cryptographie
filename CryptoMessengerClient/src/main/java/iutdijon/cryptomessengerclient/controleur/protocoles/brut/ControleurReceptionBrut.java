package iutdijon.cryptomessengerclient.controleur.protocoles.brut;

import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.network.Network;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecranbrut.EcranBrutReception;

/**
 * Controleur appelé lors de la réception d'un message par l'écran du protocole Texte Brut
 * @author simonetma
 */
public class ControleurReceptionBrut extends Controleur {

    private EcranBrutReception ecran;
    
    public ControleurReceptionBrut(EcranBrutReception ecran) {
        this.ecran = ecran;
    }
    
    
    @Override
    public void avertir() {
        Network.recevoir(ecran.getMessage());
    }
    
}
