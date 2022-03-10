package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.transposition.ComparateurCouple;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.transposition.Couple;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author nm344384
 */
public class ProtocoleTransposition extends Protocole{

    private SecureRandom generateur;
    
    /**
     * fonction qui permet de chiffrer le message
     * @param messageClair
     * @return le nouveau message
     */
    @Override
    public Message chiffrer(Message messageClair) {
        Message m = messageClair;
        //permet de vérifier que la taille de la chaine est bien de 26 et qu'elle ne contient que des lettres
        //if(this.getCle("CLE_SYMETRIQUE").matches("[a-zA-Z]+") && this.getCle("CLE_SYMETRIQUE").length() >= 5 && this.getCle("CLE_SYMETRIQUE").length() <= 20){
            String K = this.getCle("CLE_SYMETRIQUE");
            String oldBody = messageClair.getCorpsMessage();
            String NewBody = "";
            //fontion qui tri les colonnes dans le bon ordre
            ArrayList<Integer> ordre = this.getOrdreColonne(K);
            char[][] tab = this.remplirTableauChiffrement(oldBody, K);
            
            //on recupère les caractères dans l'ordre grace au tri des colonnes
            for(int col: ordre){
                for(char[] c: tab){
                    //on ajoute les caracteres 1 à 1 dans le message final
                    NewBody += c[col-1];
                }
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
     * @param messageChiffre
     * @return le nouveau message
     */
    @Override
    public Message dechiffrer(Message messageChiffre) {
        Message m = messageChiffre;
        //permet de vérifier que la taille de la chaine est bien de 26 et qu'elle ne contient que des lettres
        if(this.getCle("CLE_SYMETRIQUE").matches("[a-zA-Z]+") && this.getCle("CLE_SYMETRIQUE").length() >= 5 && this.getCle("CLE_SYMETRIQUE").length() <= 20){
            String K = this.getCle("CLE_SYMETRIQUE");
            String oldBody = messageChiffre.getCorpsMessage();
            String NewBody = ""; 
            //permet de créer un tableau a deux dimensions rempli avec le message
            char[][] tab = this.remplirTableauDechiffrement(oldBody, K);
            
            //on lit les caractères dans le tableau pour chaque ligne
            for(char[] chars: tab){
                for(char c: chars){
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
    
    /**
     * fonction qui permet de remplir le tableau de chiffrement
     * @param message correspond au message
     * @param cle correspond à la cle
     * @return un tableau à deux dimesions contenant le message
     */
    private char[][] remplirTableauChiffrement(String message,String cle){
        ByteBuffer b = ByteBuffer.allocate(4) ;
        b.putInt((cle+message).hashCode()) ;
        this.generateur = new SecureRandom (b.array()) ;
        int y = cle.length();
        int x = (int) Math.ceil((float)message.length() / (float)y);
        char[][] tab = new char[x][y];
        //pour chaque ligne
        for(int i = 0; i < x; i++){
            //pour chaque colonne
            for(int j = 0; j < y; j++){
                //permet de vérifier que l'index se trouve toujours dans le message
                if((i*y + j)< message.length()){
                    tab[i][j] = message.charAt(i*y + j);
                } 
                else {
                    tab[i][j] = this.bourrage();
                }
                
            }
        }
        return tab;
    }
    
    private char bourrage(){
        /*
        int decalage = this.generateur.nextInt(26);
        char ret;
        if(this.generateur.nextInt(2) == 0){
            ret = (char) ('a' + decalage);
        }
        else{
            ret = (char) ('A' + decalage);
        }
        return ret;
        */
        return 'X';
    }

    /**
     * permet de trier les colonnes du tableau dans l'ordre
     * @param cle correspond à la clé
     * @return les colonnes dans le bon ordre
     */
    private ArrayList<Integer> getOrdreColonne(String cle){
        ArrayList<Integer> ret = new ArrayList<>();
        ArrayList<Couple> listeCouple = new ArrayList<>();
        int indice = 1;
        //pour chaque caractères de la cle, on l'ajoute dans le tableau avec son index
        for(char c: cle.toCharArray()){
            listeCouple.add(new Couple(c,indice));
            indice ++;
        }
        //on trie ce tableau grâce à notre comparateur
        Collections.sort(listeCouple,new ComparateurCouple());
        for(Couple cp: listeCouple){
            ret.add(cp.getPosition());
        }
        return ret;
    }
    
    /**
     * fonction qui permet de remplir le tableau de déchiffrement
     * @param message correspond au message
     * @param cle correspond à la cle
     * @return un tableau à deux dimesions contenant le message déchiffré
     */
    private char[][] remplirTableauDechiffrement(String message,String cle){
        int y = cle.length();
        int x = (int) Math.ceil((float)message.length() / (float)y);
        char[][] tab = new char[x][y];
        int currentChar = 0;
        ArrayList<Integer> ordre = this.getOrdreColonne(cle);
        //pour chaque colonne
        for(int i = 0; i < y; i++){
            //pour chaque ligne
            for(int j = 0; j < x; j++){
                //on rajoute un caractère du message
                tab[j][ordre.get(i)-1] = message.charAt(currentChar);
                currentChar++;
            }
        }
        return tab;
    }
}
