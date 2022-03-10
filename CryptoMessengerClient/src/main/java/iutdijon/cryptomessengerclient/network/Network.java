package iutdijon.cryptomessengerclient.network;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * TODO Classe à coder par l'étudiant (donner le squelette)
 * @author simonetma
 */
public class Network {
          
    private static Network instance;
    private Socket socket;
    private BufferedReader fluxEntrant;
    private PrintWriter fluxSortant;
    private boolean connecte;
    
    private Network() {
        this.connecte = false;
    }
    
    /**
     * Getter du singleton
     * @return l'instance
     */
    private static Network get() {
        if (instance == null){   
            instance = new Network(); 
        }
        return instance;
    }
    
    /**
     * Création de la socket
     * @throws IOException 
     */
    private void creationSocket() throws IOException {
        //Création du socket entre client et serveur
        get().socket = new Socket(Settings.getIpServeur(),Settings.getPortServeur());
    }
    
    /**
     * Création des flux
     * @throws IOException 
     */
    private void creationFlux() throws IOException {
        //Création du gestionnaire de flux entrant
        InputStreamReader iSReader = new InputStreamReader(this.socket.getInputStream());
        this.fluxEntrant = new BufferedReader(iSReader);
        //Création du gestionnaire de flux sortant
        this.fluxSortant = new PrintWriter(this.socket.getOutputStream(),true);
    }
    
    /**
     * Connexion
     * @throws IOException 
     */
    public static void connexion() throws Exception {
        get().creationSocket();
        get().creationFlux();
        Network.recevoirLigne();
        Network.envoyerLigne(Settings.getNomUtilisateur());
        recevoirLigne();
        get().connecte = true;
        
    }
    
    /**
     * Envoyer une ligne
     * @param message le message à envoyer 
     */
    private static void envoyerLigne(String message) {
        message = message.replace('\n', '&');
        System.out.println(message);
        get().fluxSortant.println(message);
    }
    
    /**
     * Envoyer un message
     * @param message le message à envoyer au serveur
     */
    public static void envoyer(Message message) {
        try {
            Network.envoyerLigne("ENVOI");
            Network.envoyerLigne(message.getDestinataire());
            Network.envoyerLigne(message.getProtocoleUtilise().toString());
            Network.envoyerLigne(message.getCorpsMessage());
        } catch (Exception ex) {
        }
    }
    
    /**
     * Recevoir un message
     * @return le message reçu
     * @throws IOException 
     */
    private static String recevoirLigne() throws Exception {
        String message = get().fluxEntrant.readLine();
        message = message.replace('&', '\n');
        System.out.println(message);
        return message;
    }
    
    public static void recevoir(Message message) {
        try {
            Network.envoyerLigne("RECEPTION");
            message.setExpediteur(recevoirLigne());
            message.setProtocoleUtilise(recevoirLigne());
            message.setCorpsMessage(recevoirLigne());
            message.setDestinataire(Settings.getNomUtilisateur());
        } catch (Exception ex) {
        }
    } 
    
    /**
     * Le client est-il connecté ?
     * @return est-ce que le client est connecté ou non.
     */
    public static boolean estConnecte() {
        return get().connecte;
    }
}
