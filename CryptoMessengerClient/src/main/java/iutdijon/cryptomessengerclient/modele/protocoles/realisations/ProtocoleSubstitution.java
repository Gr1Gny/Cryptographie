package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author nm344384
 */
public class ProtocoleSubstitution extends Protocole {

    @Override
    public Message chiffrer(Message messageClair) {
        Message m = messageClair;
        //permet de vérifier que la taille de la chaine est bien de 26 et qu'elle ne contient que des lettres
        if(this.getCle("CLE_SYMETRIQUE").matches("[a-zA-Z]+") && this.getCle("CLE_SYMETRIQUE").length() == 26){
            String K = this.getCle("CLE_SYMETRIQUE");
            String[] Key = K.split("");
            String oldBody = messageClair.getCorpsMessage();
            String NewBody = ""; 
            for (char c: oldBody.toCharArray()) {
                //ascii a = 97 et ascii z = 122
                if (c >= 97 && c <= 122) {
                    String letter = Key[(c - 97)];
                    NewBody += letter.toLowerCase();
                //ascii A = 65 et ascii A = 90
                } else if (c >= 65 && c <= 90) {
                    String letter = Key[(c - 65)];
                    NewBody += letter;
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
        //permet de vérifier que la taille de la chaine est bien de 26 et qu'elle ne contient que des lettres
        if(this.getCle("CLE_SYMETRIQUE").matches("[a-zA-Z]+") && this.getCle("CLE_SYMETRIQUE").length() == 26){
            String K = this.getCle("CLE_SYMETRIQUE");
            ArrayList<String> Key = new ArrayList<String>(Arrays.asList(K.split("")));
            String oldBody = messageChiffre.getCorpsMessage();
            String NewBody = ""; 
            for (char c: oldBody.toCharArray()) {
                //ascii a = 97 et ascii z = 122
                if (c >= 97 && c <= 122) {
                    int decalage = Key.indexOf(Character.toString(c).toUpperCase()); 
                    char letter = (char)(97 + decalage);
                    NewBody += Character.toLowerCase(letter);
                //ascii A = 65 et ascii A = 90
                } else if (c >= 65 && c <= 90) {
                    int decalage = Key.indexOf(Character.toString(c));    
                    char letter = (char)(65 + decalage);
                    NewBody += letter;
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
