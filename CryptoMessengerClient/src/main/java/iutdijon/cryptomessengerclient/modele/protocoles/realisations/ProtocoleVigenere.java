package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;

/**
 *
 * @author Noham
 */
public class ProtocoleVigenere extends Protocole {

    /**
     * fonction qui permet de chiffrer le message
     * @param messageClair
     * @return le nouveau message
     */
    @Override
    public Message chiffrer(Message messageClair) {
        Message m = messageClair;
        //permet de vérifier que la taille de la chaine est bien de 26 et qu'elle ne contient que des lettres
        //if(this.getCle("CLE_SYMETRIQUE").matches("[A-Z]+") && this.getCle("CLE_SYMETRIQUE").length() >= 5 && this.getCle("CLE_SYMETRIQUE").length() <= 20){       
            String oldBody = messageClair.getCorpsMessage();
            String NewBody = ""; 
            String K = repeterCle(oldBody,this.getCle("CLE_SYMETRIQUE"));
            int CurrentChar = 0;
            for(char c: oldBody.toCharArray()){
                //ascii A = 65 et ascii A = 90
                if (c >= 65 && c <= 90) {
                    //on chiffre le caractère grâce à la clé
                    char newChar = tableauChiffrement(c, K.charAt(CurrentChar));
                    NewBody += newChar;
                }        
                else{
                    NewBody += c;
                }
                CurrentChar ++;
            }
            
            m = new Message();
            m.setDestinataire(messageClair.getDestinataire());
            m.setExpediteur(messageClair.getExpediteur());
            m.setCorpsMessage(NewBody);
            m.setProtocoleUtilise(messageClair.getProtocoleUtilise().toString());
        //}     
        return m;
    }

    /**
     * fonction qui permet de déchiffrer le message
     * @param messageClair
     * @return le nouveau message
     */
    @Override
    public Message dechiffrer(Message messageChiffre) {
        Message m = messageChiffre;
        //permet de vérifier que la taille de la chaine est bien de 26 et qu'elle ne contient que des lettres
        //if(this.getCle("CLE_SYMETRIQUE").matches("[A-Z]+") && this.getCle("CLE_SYMETRIQUE").length() >= 5 && this.getCle("CLE_SYMETRIQUE").length() <= 20){       
            String oldBody = messageChiffre.getCorpsMessage();
            String NewBody = ""; 
            String K = repeterCle(oldBody,this.getCle("CLE_SYMETRIQUE"));
            int CurrentChar = 0;
            for(char c: oldBody.toCharArray()){
                //ascii A = 65 et ascii A = 90
                if (c >= 65 && c <= 90) {
                    //permet de déchiffrer le caractère grâce à la clé
                    char newChar = tableauDechiffrement(c, K.charAt(CurrentChar));
                    NewBody += newChar;
                }        
                else{
                    NewBody += c;
                }
                CurrentChar ++;
            }
            
            m = new Message();
            m.setDestinataire(messageChiffre.getDestinataire());
            m.setExpediteur(messageChiffre.getExpediteur());
            m.setCorpsMessage(NewBody);
            m.setProtocoleUtilise(messageChiffre.getProtocoleUtilise().toString());
        //}     
        return m;
    }
    
    /**
     * permet de répéter la clé pour qu'elle soit plus longue que le message
     * @param message correspond au message 
     * @param cle correspond à la clé
     * @return le masque jetable
     */
    private String repeterCle(String message,String cle){
        while(cle.length() < message.length()){
            cle += cle;
        }
        return cle;
    }
    
    /**
     * permet de chiffrer un caractère grâce à la clé
     * @param a correspond au caractère
     * @param cle correspond à la clé
     * @return le caractère chiffré
     */
    private char tableauChiffrement(char a, char cle){
        //on calcule ici le décalage
        int decalage = (a - 65 + cle - 65) % 26;
        return (char) ('A' + decalage);
    }

    /**
     * permet de déchiffrer un caractère grâce à la clé
     * @param a correspond au caractère
     * @param cle correspond à la clé
     * @return le caractère déchiffré
     */
    private char tableauDechiffrement(char a, char cle) {
        //on calcule ici le décalage
        int decalage = (a - 65 - cle - 65) % 26;
        if(decalage < 0){
            decalage += 26;
        }
        return (char) ('A' + decalage);
    }
    
}
