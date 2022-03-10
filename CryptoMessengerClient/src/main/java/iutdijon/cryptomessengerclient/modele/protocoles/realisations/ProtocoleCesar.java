package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;

/**
 *
 * @author Noham
 */
public class ProtocoleCesar extends Protocole{

    @Override
    public Message chiffrer(Message messageClair) {
        Message m = messageClair;
        //permet de vérifier que la chaine contient bien un entier positif
        if(this.getCle("CLE_SYMETRIQUE").matches("[+]?\\d*(\\.\\d+)?")){
            int K = Integer.valueOf(this.getCle("CLE_SYMETRIQUE"));
            String oldBody = messageClair.getCorpsMessage();
            String NewBody = ""; 
            for (char c: oldBody.toCharArray()) {
                //ascii a = 97 et ascii z = 122
                if (c >= 97 && c <= 122) {
                    int decalage = (c - 97 + K) % 26;
                    NewBody += (char)(97 + decalage);
                //ascii A = 65 et ascii A = 90
                } else if (c >= 65 && c <= 90) {
                    int decalage = (c - 65 + K) % 26;
                    NewBody += (char)(65 + decalage);
                } else{
                    NewBody += c;
                }
            }
            m = new Message();
            m.setDestinataire(messageClair.getDestinataire());
            m.setExpediteur(messageClair.getExpediteur());
            m.setCorpsMessage(NewBody);
            m.setProtocoleUtilise(messageClair.getProtocoleUtilise().toString());
        }     
        return m;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        Message m = messageChiffre;
        //permet de vérifier que la chaine contient bien un entier positif
        if(this.getCle("CLE_SYMETRIQUE").matches("[+]?\\d*(\\.\\d+)?")){
            int K = Integer.valueOf(this.getCle("CLE_SYMETRIQUE"));
            String oldBody = messageChiffre.getCorpsMessage();
            String NewBody = ""; 
            for (char c: oldBody.toCharArray()) {
                //ascii a = 97 et ascii z = 122
                if (c >= 97 && c <= 122) {
                    int decalage = (c - 97 - K) % 26;
                    if(decalage < 0) decalage += 26;
                    NewBody += (char)(97 + decalage);
                //ascii A = 65 et ascii A = 90
                } else if (c >= 65 && c <= 90) {
                    int decalage = (c - 65 - K) % 26;
                    if(decalage < 0) decalage += 26;
                    NewBody += (char)(65 + decalage);
                } else{
                    NewBody += c;
                }
            }
            m = new Message();
            m.setDestinataire(messageChiffre.getDestinataire());
            m.setExpediteur(messageChiffre.getExpediteur());
            m.setCorpsMessage(NewBody);
            m.setProtocoleUtilise(messageChiffre.getProtocoleUtilise().toString());
        }     
        return m;
    }
    
}
