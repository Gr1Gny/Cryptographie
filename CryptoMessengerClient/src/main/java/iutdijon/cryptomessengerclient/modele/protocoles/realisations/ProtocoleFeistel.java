package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;

/**
 *
 * @author nm344384
 */
public class ProtocoleFeistel extends Protocole {

    @Override
    public Message chiffrer(Message messageClair) {
        Message m = messageClair;
        //permet de vérifier que la taille de la chaine est bien de 26 et qu'elle ne contient que des lettres
        //if(this.getCle("CLE_SYMETRIQUE").matches("[A-Z]+") && this.getCle("CLE_SYMETRIQUE").length() >= 5 && this.getCle("CLE_SYMETRIQUE").length() <= 20){       
            String oldBody = messageClair.getCorpsMessage();
            String K = this.getCle("CLE_SYMETRIQUE");
            
            String M1 = this.couper1(oldBody);
            String M2 = this.couper2(oldBody);
            String K1 = this.couper1(K);
            String K2 = this.couper2(K);
            
            String TM2 = this.T(M2, K1);
            String I2 = this.S(M1, TM2);
            String I1 = M2;
            
            String TI2 = this.T(I2, K1);
            String C2 = this.S(I1, TI2);
            String C1 = I2;
            
            String NewBody = C1 + C2;
            
            m = new Message();
            m.setDestinataire(messageClair.getDestinataire());
            m.setExpediteur(messageClair.getExpediteur());
            m.setCorpsMessage(NewBody);
            m.setProtocoleUtilise(messageClair.getProtocoleUtilise().toString());
        //}     
        return m;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        Message m = messageChiffre;
        //permet de vérifier que la taille de la chaine est bien de 26 et qu'elle ne contient que des lettres
        //if(this.getCle("CLE_SYMETRIQUE").matches("[A-Z]+") && this.getCle("CLE_SYMETRIQUE").length() >= 5 && this.getCle("CLE_SYMETRIQUE").length() <= 20){       
            String oldBody = messageChiffre.getCorpsMessage();
            String K = this.getCle("CLE_SYMETRIQUE");
            
            String C1 = this.couper1(oldBody);
            String C2 = this.couper2(oldBody);
            String K1 = this.couper1(K);
            String K2 = this.couper2(K);
            
            String TC1 = this.T(C1, K2);
            String I1 = this.Sbarre(C2, TC1);
            String I2 = C1;
            
            String TI1 = this.T(I1, K1);
            String M1 = this.Sbarre(I2, TI1);
            String M2 = I1;
            
            String NewBody = M1 + M2;
            
            m = new Message();
            m.setDestinataire(messageChiffre.getDestinataire());
            m.setExpediteur(messageChiffre.getExpediteur());
            m.setCorpsMessage(NewBody);
            m.setProtocoleUtilise(messageChiffre.getProtocoleUtilise().toString());
        //}     
        return m;
    }
    
    private String couper1(String str){
        String ret = "";
        for(int i = 0; i < Math.ceil(str.length()/2.0); i++){
            ret += str.charAt(i);
        }
        return ret;
    }
    
    private String couper2(String str){
        String ret = "";
        for(int i = (int) Math.ceil(str.length()/2.0); i < str.length(); i++){
            ret += str.charAt(i);
        }
        return ret;
    }
    
    private String T(String message,String cle){
        ProtocoleTransposition T = new ProtocoleTransposition();    
        T.ajouterCle("CLE_SYMETRIQUE", cle);
        Message msg = new Message();
        msg.setCorpsMessage(message);
        msg = T.chiffrer(msg);
        message = msg.getCorpsMessage();
        return message;
    }
    
    private String S(String message,String cle){
        ProtocoleVigenere S = new ProtocoleVigenere();   
        S.ajouterCle("CLE_SYMETRIQUE", cle);
        Message msg = new Message();
        msg.setCorpsMessage(message);
        msg = S.chiffrer(msg);
        message = msg.getCorpsMessage();
        return message;
    }
    
    private String Sbarre(String message,String cle){
        ProtocoleVigenere S = new ProtocoleVigenere();   
        S.ajouterCle("CLE_SYMETRIQUE", cle);
        Message msg = new Message();
        msg.setCorpsMessage(message);
        msg = S.dechiffrer(msg);
        message = msg.getCorpsMessage();
        return message;
    }
}
