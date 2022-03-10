package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;

/**
 *
 * @author nm344384
 */
public class ProtocoleBrut extends Protocole {

    @Override
    public Message chiffrer(Message messageClair) {
        return messageClair;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        return messageChiffre;
    }
    
}
