package iutdijon.cryptomessengerclient.controleur.protocoles.brut;

import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.FabriqueProtocole;
import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.network.Network;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecranbrut.EcranBrutEnvoi;

/**
 * Controleur appelé lors de l'envoie d'un message par l'écran du protocole Texte Brut
 * @author simonetma
 */
public class ControleurEnvoieBrut extends Controleur {

    private EcranBrutEnvoi ecran;
    
    public ControleurEnvoieBrut(EcranBrutEnvoi ecran) {
        this.ecran = ecran;
    }
    
    @Override
    public void avertir() {
        //On chiffre le message
        Message messageChiffre = FabriqueProtocole.create(NomProtocole.BRUT).chiffrer(ecran.getMessage());
        //On l'envoie
        Network.envoyer(messageChiffre);
    }
    
}
