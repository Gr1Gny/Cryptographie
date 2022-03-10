package iutdijon.cryptomessengerclient.modele.protocoles;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import java.util.HashMap;

/**
 * classe abstraite des protocoles de cryptographie
 * @author simonetma
 */
public abstract class Protocole {
    
    private HashMap<String,String> cles;    //HashMap des clés du protocoles
    
    /**
     * Constructeur
     */
    public Protocole() {
        this.cles = new HashMap<>();
    }
    
    /**
     * Ajoute une clé au trousseau de clé
     * @param nomCle nom de la clé
     * @param valeurCle valeur de la clé
     */
    public void ajouterCle(String nomCle, String valeurCle) {
        cles.put(nomCle, valeurCle);
    }
    
    /**
     * Renvoie la clé demandée
     * @param nomCle le nom de la clé désirée
     * @return la clé
     */
    protected String getCle(String nomCle) {
        return this.cles.get(nomCle);
    }
    
    /**
     * Chiffre un message
     * @param messageClair le message à chiffrer
     * @return 
     */
    public abstract Message chiffrer(Message messageClair);
    
    /**
     * Dechiffre un message
     * @param messageChiffre message à dechiffrer
     * @return 
     */
    public abstract Message dechiffrer(Message messageChiffre);
}
