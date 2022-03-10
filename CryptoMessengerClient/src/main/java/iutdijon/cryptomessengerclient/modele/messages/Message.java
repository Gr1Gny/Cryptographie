package iutdijon.cryptomessengerclient.modele.messages;

import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.controleur.observation.Observable;

/**
 * Message pouvant circuler entre le client et le serveur
 * @author simonetma
 */
public class Message extends Observable {
    
    private String expediteur;                  //Qui a envoyé le message
    private String destinataire;                //Qui a reçu le message
    private NomProtocole protocoleUtilise;      //Protocole de crypto utilisé
    private String corpsMessage;               //Message (chiffré)
    
    /**
     * Constructuer d'un message vide
     */
    public Message() {
        this.expediteur = "";
        this.destinataire = "";
        this.protocoleUtilise = NomProtocole.BRUT;
        this.corpsMessage = "";
    }
    
    /**
     * Constructeur par clonage
     * @param message modèle
     */
    public Message(Message message) {
        this.expediteur = message.expediteur;
        this.destinataire = message.destinataire;
        this.protocoleUtilise = message.protocoleUtilise;
        this.corpsMessage = message.corpsMessage;
    }

    /**
     * Getter de l'expéditeur
     * @return l'expéditeur du message
     */
    public String getExpediteur() {
        return expediteur;
    }

    /**
     * Setter de l'expéditeur
     * @param expediteur l'expéditeur du message
     */
    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
        this.avertir("EXPEDITEUR");
    }

    /**
     * Getter du destinataire
     * @return destinataire du message
     */
    public String getDestinataire() {
        return destinataire;
    }

    /**
     * Setter du destinataire
     * @param destinataire destinataire du message
     */
    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
        this.avertir("DESTINATAIRE");
    }

    /**
     * Getter du protocole
     * @return protocole de crypto du message
     */
    public NomProtocole getProtocoleUtilise() {
        return protocoleUtilise;
    }

    /**
     * Setter du protocole
     * @param NomProtocoleUtilise protocole de crypto du message 
     */
    public void setProtocoleUtilise(String NomProtocoleUtilise) {
        this.protocoleUtilise = NomProtocole.valueOf(NomProtocoleUtilise);
        this.avertir("PROTOCOLE");
    }

    /**
     * Getter du corps du message
     * @return le corps du message
     */
    public String getCorpsMessage() {
        return corpsMessage;
    }

    /**
     * Setter du corps du message
     * @param corpsMessage le corps du message
     */
    public void setCorpsMessage(String corpsMessage) {
        this.corpsMessage = corpsMessage;
        this.avertir("CORPS");
    }
    
    
    
}
