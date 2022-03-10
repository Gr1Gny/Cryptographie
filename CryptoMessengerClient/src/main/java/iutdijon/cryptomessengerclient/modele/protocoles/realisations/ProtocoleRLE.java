package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;

/**
 *
 * @author nm344384
 */
public class ProtocoleRLE extends Protocole {

    
    @Override
    public Message chiffrer(Message messageClair) {
        Message m = messageClair;
        String K = this.getCle("CLE_COMPRESSION");
        if(Integer.valueOf(K) >= 2){
            String oldBody = messageClair.getCorpsMessage();
            String NewBody = ""; 
            char Currentchar = oldBody.charAt(0);
            int repet = 0;
            String repetition = "";
            for (char c: oldBody.toCharArray()) {
                //permet de verifier qu'on répète toujours le meme char mais qu'on ne dépasse pas la valeur de la cle
                if(Currentchar == c && repet < Integer.valueOf(K)){
                    repet ++;
                }
                else{
                    //si un nouveau char apparait, on note dans le message le precedent char avec son nombre de répetition
                    NewBody += RajouteZero(K, repet) + Currentchar;
                    repet = 1;
                    Currentchar = c;
                }
            }
            //on rajoute le nombre de répétition du dernier char au message
            NewBody += RajouteZero(K, repet) + Currentchar;
            
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
        String oldBody = messageChiffre.getCorpsMessage();
        String NewBody = ""; 
        int lenghtK = this.getCle("CLE_COMPRESSION").length();
        //on fait cette boucle pour diviser le message par la taille de la clé + 1(le char)
        for (int i = 0; i < oldBody.length()/(lenghtK+1); i++) {
            String repet = "";
            //on récupère le nombre de répétition grace à sa taille déduite par celle de la clé
            for(int h = 0; h < lenghtK; h++){
                repet += String.valueOf(oldBody.charAt((lenghtK+1)*i + h));
            }        
            //on récupère le caractère qui se trouve juste apres le nombre récupéré precedemment
            char currentchar = oldBody.charAt((lenghtK+1)*i + lenghtK);
            for(int j = 0; j < Integer.valueOf(repet); j ++){
                NewBody += currentchar;
            }
        }
        m = new Message();
        m.setDestinataire(messageChiffre.getDestinataire());
        m.setExpediteur(messageChiffre.getExpediteur());
        m.setCorpsMessage(NewBody);
        m.setProtocoleUtilise(messageChiffre.getProtocoleUtilise().toString());   
        return m;
    }
    
    /**
     * fonction qui permet de rajouter des zeros afin que la repetition fasse la meme taille que la clé
     * @param K correspond à la clé
     * @param repet correspond au nombre de répétition
     * @return un chaine de caractères contenant le nombre de répétitions de la bonne taille
     */
    private String RajouteZero(String K, int repet){
        String repetition = "";
        while (repetition.length() < K.length() - String.valueOf(repet).length()) {
            repetition += "0";
        }
        repetition += String.valueOf(repet);
        return repetition;
    }
    
}
